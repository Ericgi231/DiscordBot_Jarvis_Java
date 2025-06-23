package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

import static me.ericgi231.helper.ActionHelper.GetRandomEmojis;

public class RandomEmojisAction {
    private final static int emojiCount = 21;

    public static MessageContent action(final ArrayList<String> ignoredWords) {
        return new MessageContentBuilder().setText(GetRandomEmojis().repeat(emojiCount)).build();
    }
}
