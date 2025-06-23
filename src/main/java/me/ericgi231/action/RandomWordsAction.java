package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static me.ericgi231.constant.PathConstant.PATH_TO_DICT;
import static me.ericgi231.helper.ActionHelper.GetRandomEmojis;
import static me.ericgi231.helper.ActionHelper.GetRandomWordsFromResourceFile;

public class RandomWordsAction {
    private final static int MIN_WORDS = 1;
    private final static int MAX_WORDS = 5;
    private final static String DELIM = " ";

    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        final var rand = ThreadLocalRandom.current();
        return new MessageContentBuilder()
                .setText(GetRandomWordsFromResourceFile(PATH_TO_DICT, rand.nextInt(MIN_WORDS, MAX_WORDS), DELIM) + DELIM + GetRandomEmojis())
                .build();
    }
}
