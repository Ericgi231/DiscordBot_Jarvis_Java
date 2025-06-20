package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

import static me.ericgi231.constant.ActionStringMethodConstantMap.ACTION_STRING_METHOD_MAP;

public class HelpMessageAction {
    private static final String PRE_MESSAGE = "Valid keywords: ";

    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        StringBuilder stringBuilder = new StringBuilder().append(PRE_MESSAGE);
        ACTION_STRING_METHOD_MAP.forEach((s, action) -> stringBuilder.append(s).append(", "));
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return new MessageContentBuilder().setText(stringBuilder.toString()).build();
    }
}
