package me.ericgi231.helper;

import net.dv8tion.jda.api.utils.FileUpload;
import net.fellbaum.jemoji.EmojiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static FileUpload GetLocalFile(String fileName) {
        return FileUpload.fromData(new File(PATH_TO_COLLECTION + fileName), fileName);
    }

    public static String GetRandomWordsFromFile(String filePath) throws RuntimeException {
        return GetRandomWordsFromFile(filePath, 1, "");
    }

    public static String GetRandomWordsFromFile(String filePath, int numberOfWords, String del) throws RuntimeException {
        //TODO Fix temp throwing of runtime exception
        try {
            final var dict = Files.readAllLines(Paths.get(filePath));
            final var rand = ThreadLocalRandom.current();
            var builder = new StringBuilder();
            for (int i = 0; i < numberOfWords; i++) {
                builder.append(dict.get(rand.nextInt(dict.size()))).append(del);
            }
            builder.delete(builder.length() - del.length(), builder.length());
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String GetRandomEmojis() {
        return GetRandomEmojis(1);
    }

    public static String GetRandomEmojis(int quantity) {
        final var emojis = EmojiManager.getAllEmojis().stream().toList();
        final var rand = ThreadLocalRandom.current();
        final var emoji = emojis.get(rand.nextInt(emojis.size()));
        return emoji.getEmoji().repeat(quantity);
    }
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
