package me.ericgi231.action;

import com.jayway.jsonpath.JsonPath;
import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;
import java.util.Objects;

import static me.ericgi231.helper.ActionHelper.SendHTTPRequest;

public class DuckDuckGoSearchAction {
    private static final String JSON_PATH_TO_URL = "$.AbstractText";
    private static final String RANDOM_REQUEST_URI = "https://api.duckduckgo.com/?format=json&q=cats";
    private static final String PRE_REQUEST_URI = "https://api.duckduckgo.com/?format=json&q=";
    private static final String HTTP_FAILED_MESSAGE = "That does not exist.";
    private static final String RESULT_NO_INSTANT_ANSWER = "No instant answer available.";

    //TODO This kinda sucks, duckduck go api rarely returns instant abstract text, need to find a better api for searches
    public static MessageContent action(final ArrayList<String> words) {
        String request_uri;
        if (!words.isEmpty()) {
            request_uri = PRE_REQUEST_URI + String.join("%20", words);
        } else {
            request_uri = RANDOM_REQUEST_URI;
        }
        try {
            var httpResponse = SendHTTPRequest(request_uri);
            if (httpResponse.statusCode() == 200) {
                String instantAnswer = JsonPath.parse(httpResponse.body()).read(JSON_PATH_TO_URL).toString();
                if (Objects.equals(instantAnswer, "")) {
                    instantAnswer = RESULT_NO_INSTANT_ANSWER;
                }
                return new MessageContentBuilder().setText(instantAnswer).build();
            } else {
                return new MessageContentBuilder().setText(HTTP_FAILED_MESSAGE).build();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
