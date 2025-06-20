package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

import static me.ericgi231.constant.PathConstant.PATH_TO_CURSES;
import static me.ericgi231.helper.ActionHelper.GetRandomWordsFromFile;

public class ThanksMessageAction {
    private final static String PRE_MESSAGE = "You're welcome, ";

    public static MessageContent Action(final ArrayList<String> ignoredWords) throws RuntimeException {
        return new MessageContentBuilder().setText(PRE_MESSAGE + GetRandomWordsFromFile(PATH_TO_CURSES)).build();
    }
}
