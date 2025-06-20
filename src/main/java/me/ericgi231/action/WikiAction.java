package me.ericgi231.action;

import com.jayway.jsonpath.JsonPath;
import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

import static me.ericgi231.helper.ActionHelper.SendHTTPRequest;

public class WikiAction {
    private static final String JSON_PATH_TO_URL = "$.content_urls.desktop.page";
    private static final String RANDOM_REQUEST_URI = "https://en.wikipedia.org/api/rest_v1/page/random/summary";
    private static final String PRE_REQUEST_URI = "https://en.wikipedia.org/api/rest_v1/page/summary/";
    private static final String HTTP_FAILED_MESSAGE = "That does not exist.";

    public static MessageContent Action(final ArrayList<String> words) {
        String request_uri;
        if (!words.isEmpty()) {
            request_uri = PRE_REQUEST_URI + String.join("_", words);
        } else {
            request_uri = RANDOM_REQUEST_URI;
        }
        try {
            var httpResponse = SendHTTPRequest(request_uri);
            if (httpResponse.statusCode() == 200) {
                var url = JsonPath.parse(httpResponse.body()).read(JSON_PATH_TO_URL).toString();
                return new MessageContentBuilder().setText(url).build();
            } else {
                return new MessageContentBuilder().setText(HTTP_FAILED_MESSAGE).build();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
