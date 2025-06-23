package me.ericgi231.helper;

import net.dv8tion.jda.api.utils.FileUpload;
import net.fellbaum.jemoji.EmojiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ThreadLocalRandom;

import static java.net.URI.create;
import static me.ericgi231.constant.PathConstant.PATH_TO_COLLECTION;

public final class ActionHelper {
    private static final Logger logger = LoggerFactory.getLogger(ActionHelper.class);
    private static final String LOG_HTTP_REQUEST = "HTTP Request: {}";
    private static final String LOG_HTTP_RESPONSE_CODE = "HTTP response code: {}";
    private static final String LOG_HTTP_RESPONSE_BODY = "HTTP response body: {}";

    private static final String USER_AGENT_KEY = "User-Agent";
    private static final String USER_AGENT_VALUE = "ericgi231@gmail.com";

    private ActionHelper() {}

    /**
     * Retrieve a file from the collection specified in system environment variable.
     * @param fileName name of file to get from collection
     * @return file from local collection folder
     */
    public static FileUpload GetLocalFile(String fileName) {
        return FileUpload.fromData(new File(PATH_TO_COLLECTION + fileName), fileName);
    }

    /**
     * Read a random word from a resource file
     * @param filePath name of file to get from resources
     * @return single word
     */
    public static String GetRandomWordsFromResourceFile(String filePath) {
        return GetRandomWordsFromResourceFile(filePath, 1, "");
    }

    /**
     * Read a random selection of words from a resource file appended with deliminators
     * @param filePath name of file to get from resources
     * @param numberOfWords number of words to read
     * @param del deliminator to append after each word
     * @return deliminated words
     */
    public static String GetRandomWordsFromResourceFile(String filePath, int numberOfWords, String del) {
        InputStream inStream = ActionHelper.class.getClassLoader().getResourceAsStream(filePath);
        if (inStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            var text = reader.lines().toList();
            final var rand = ThreadLocalRandom.current();
            var builder = new StringBuilder();
            for (int i = 0; i < numberOfWords; i++) {
                builder.append(text.get(rand.nextInt(text.size()))).append(del);
            }
            builder.delete(builder.length() - del.length(), builder.length());
            return builder.toString();
        }
        //TODO Handle if input stream fails
        return "";
    }

    /**
     * Get a random emoji
     * @return single emoji
     */
    public static String GetRandomEmojis() {
        return GetRandomEmojis(1, "");
    }

    /**
     * Get a random selection of emojis appended with a deliminator
     * @param quantity number of emoji
     * @return deliminated emojis
     */
    public static String GetRandomEmojis(int quantity, String del) {
        final var emojis = EmojiManager.getAllEmojis().stream().toList();
        final var rand = ThreadLocalRandom.current();
        final var builder = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            builder.append(emojis.get(rand.nextInt(emojis.size())).getEmoji()).append(del);
        }
        builder.delete(builder.length() - del.length(), builder.length());
        return builder.toString();
    }

    /**
     * Send an HTTP Request and return response
     * @param request_uri URI of request
     * @return HTTP response
     */
    public static HttpResponse<String> SendHTTPRequest(String request_uri) {
        try {
            HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader(USER_AGENT_KEY, USER_AGENT_VALUE)
                    .uri(create(request_uri))
                    .build();
            logger.info(LOG_HTTP_REQUEST, request);
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info(LOG_HTTP_RESPONSE_CODE, httpResponse.statusCode());
            logger.info(LOG_HTTP_RESPONSE_BODY, httpResponse.body());
            return httpResponse;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
