package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RandomDateTimeAction {
    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        final var now = new Date().getTime();
        final var today = new Date(now);
        final var twoYearLater = new Date(now + TimeUnit.DAYS.toMillis(730));
        final var randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(today.getTime(), twoYearLater.getTime());
        return new MessageContentBuilder().setText(new Date(randomMillisSinceEpoch).toString()).build();
    }
}
