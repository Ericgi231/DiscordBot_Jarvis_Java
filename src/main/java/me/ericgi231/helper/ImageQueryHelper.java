package me.ericgi231.helper;

import com.jayway.jsonpath.JsonPath;
import me.ericgi231.dataType.ImageQueryData;
import net.dv8tion.jda.api.utils.FileUpload;
import net.minidev.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static me.ericgi231.constant.ApiConstant.UNSPLASH_TOKEN;
import static me.ericgi231.constant.PathConstant.PATH_TO_COLLECTION;
import static me.ericgi231.helper.ActionHelper.SendHTTPRequest;
import static me.ericgi231.helper.GlobalHelper.cleanString;

public final class ImageQueryHelper {
    private static final Logger logger = LoggerFactory.getLogger(ImageQueryHelper.class);

    private static final long MAX_SIZE = 8000000;
    private static final long MAX_FAILS = 20;

    private static final String JSON_PATH_TO_UNSPLASH_URLS = "$[*].['urls'].['regular']";
    private static final String JSON_PATH_TO_UNSPLASH_SLUGS = "$[*].['slug']";

    private ImageQueryHelper() {}

    public static List<FileUpload> getStockImages(ImageQueryData imageQueryData) {
        final List<FileUpload> files = new ArrayList<>();
        var builder = new StringBuilder()
                .append("https://api.unsplash.com/photos/random/")
                .append("?count=")
                .append(imageQueryData.quantity())
                .append("&client_id=")
                .append(UNSPLASH_TOKEN);
        if (imageQueryData.hasTerms()) {
            assert imageQueryData.terms() != null;
            builder.append("&query=").append(String.join("%20", imageQueryData.terms()));
        }
        var httpResponse = SendHTTPRequest(builder.toString());
        if (httpResponse.statusCode() == 200) {
            var responseImageUrls = toStringArray(JsonPath.parse(httpResponse.body()).read(JSON_PATH_TO_UNSPLASH_URLS));
            var responseImageSlugs = toStringArray(JsonPath.parse(httpResponse.body()).read(JSON_PATH_TO_UNSPLASH_SLUGS));
            for (int i = 0; i < responseImageUrls.length; i++) {
                responseImageSlugs[i] = responseImageSlugs[i] + "." + getFileTypeFromUnsplashQuery(responseImageUrls[i]);
            }
            files.addAll(getFilesFromUrls(responseImageUrls, responseImageSlugs));
        }
        return files;
    }

    public static List<FileUpload> getPornImages(ImageQueryData imageQueryData) {
        final List<FileUpload> files = new ArrayList<>();
        var rand =  ThreadLocalRandom.current();
        var stringBuilder = new StringBuilder()
                .append("https://api.rule34.xxx/index.php?page=dapi&s=post&q=index");
        if (imageQueryData.hasTerms()) {
            assert imageQueryData.terms() != null;
            stringBuilder.append("&tags=").append(String.join("%20", imageQueryData.terms()));
        }
        var httpResponse = SendHTTPRequest(stringBuilder.toString());
        if (httpResponse.statusCode() == 200) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = factory.newDocumentBuilder();
                InputStream inputStream = new ByteArrayInputStream(httpResponse.body().getBytes());
                Document doc = docBuilder.parse(inputStream);
                NodeList nodeList = doc.getElementsByTagName("post");
                String[] urls = new String[imageQueryData.quantity()];
                String[] names = new String[imageQueryData.quantity()];
                for (int i = 0; i < imageQueryData.quantity(); i++) {
                    var num = rand.nextInt(nodeList.getLength());
                    urls[i] = nodeList.item(num).getAttributes().getNamedItem("sample_url").getNodeValue();
                    names[i] = StringUtils.substringAfterLast(urls[i], "/");
                }
                logger.info("urls: {}", Arrays.toString(urls));
                files.addAll(getFilesFromUrls(urls, names));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return files;
    }

    @NotNull
    public static List<FileUpload> getMemeImages(@NotNull ImageQueryData imageQueryData) {
        final var collection = new File(PATH_TO_COLLECTION);
        final File[] files;
        if (imageQueryData.hasTerms()) {
            assert imageQueryData.terms() != null;
            files = collection.listFiles((dir, name) -> cleanString(name)
                    .contains(cleanString(String.join("", imageQueryData.terms()))));
        } else {
            files = collection.listFiles();
        }
        if (files == null || files.length == 0) {
            return List.of();
        } else {
            return getRandomFilesFromFileArray(List.of(files), imageQueryData.quantity());
        }
    }

    //TODO Improve logic for handling oversized files
    private static List<FileUpload> getRandomFilesFromFileArray(List<File> files, int count) {
        final var rand = ThreadLocalRandom.current();
        var failedAttempts = 0;
        final var images = new ArrayList<FileUpload>();
        if (!files.isEmpty()) {
            for (int i = 0; i < count && failedAttempts < MAX_FAILS; i++) {
                var index = rand.nextInt(files.size());
                var file = files.get(index);
                if (file.length() < MAX_SIZE) {
                    images.add(FileUpload.fromData(file, file.getName()));
                } else {
                    failedAttempts++;
                    i--;
                }
            }
        }
        return images;
    }

    private static String getFileTypeFromUnsplashQuery(String query) {
        return StringUtils.substringBetween(query, "fm=", "&");
    }

    //TODO Clean this up to better detect sizes
    private static List<FileUpload> getFilesFromUrls(String[] urls, String[] names) {
        ArrayList<FileUpload> files = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            try {
                URL url = URI.create(urls[i]).toURL();
                URLConnection connection = url.openConnection();
                InputStream stream1 = connection.getInputStream();
                byte[] bytes = IOUtils.toByteArray(stream1);
                //TODO Improve logging across code
                logger.info("Bytes: {}, URL: {}", bytes.length, url);
                if (bytes.length < MAX_SIZE) {
                    final InputStream stream2 = new ByteArrayInputStream(bytes);
                    Supplier<InputStream> inputStreamSupplier = () -> stream2;
                    var fileUpload = FileUpload.fromStreamSupplier(names[i], inputStreamSupplier);
                    files.add(fileUpload);
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return files;
    }

    public static String[] toStringArray(JSONArray array) {
        if(array==null)
            return new String[0];

        String[] arr=new String[array.size()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=array.get(i).toString();
        }
        return arr;
    }
}
