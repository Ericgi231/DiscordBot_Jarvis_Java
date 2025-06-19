package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;
import me.ericgi231.helper.ActionHelper;
import java.util.ArrayList;

public class InvalidAction {
    private static final String MESSAGE = "Feature currently unimplemented, also you're gay :)";
    private static final String FILE_NAME = "207.png";

    public static MessageContent Action(ArrayList<String> ignoredWords) {
        return new MessageContentBuilder().setText(MESSAGE).setFiles(ActionHelper.GetLocalFile(FILE_NAME)).build();
    }
}
