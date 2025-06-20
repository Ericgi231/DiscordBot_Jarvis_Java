package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;
import java.util.ArrayList;

public class HelloMessageAction {
    private static final String MESSAGE = "Shut the fuck lil dick and go suck on your mummy's titties and stop wasting my time. Your dog is as ugly as you !! Don't waste my time again.";

    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        return new MessageContentBuilder().setText(MESSAGE).build();
    }
}
