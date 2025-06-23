package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

import static me.ericgi231.constant.ActionConstant.STRING_ACTION_FUNCTION_MAP;

public class HelpMessageAction {
    private static final String PRE_MESSAGE = "Valid keywords: ";

    public static MessageContent action(final ArrayList<String> ignoredWords) {
        StringBuilder stringBuilder = new StringBuilder().append(PRE_MESSAGE);
        STRING_ACTION_FUNCTION_MAP.forEach((s, action) -> stringBuilder.append(s).append(", "));
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return new MessageContentBuilder().setText(stringBuilder.toString()).build();
    }
}
