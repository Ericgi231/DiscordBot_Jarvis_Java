package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static me.ericgi231.constant.PathConstant.PATH_TO_DICT;
import static me.ericgi231.helper.ActionHelper.GetRandomEmojis;
import static me.ericgi231.helper.ActionHelper.GetRandomWordsFromFile;

public class RandomWordsAction {
    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        final var rand = ThreadLocalRandom.current();
        return new MessageContentBuilder()
                .setText(GetRandomWordsFromFile(PATH_TO_DICT, rand.nextInt(1, 5), " ") + " " + GetRandomEmojis())
                .build();
    }
}
