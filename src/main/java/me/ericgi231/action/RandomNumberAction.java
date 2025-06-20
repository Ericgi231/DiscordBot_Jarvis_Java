package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberAction {
    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        return new MessageContentBuilder().setText(String.valueOf(ThreadLocalRandom.current().nextInt())).build();
    }
}
