package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class RandomEightBallAction {
    private static final List<String> outcomes = List.of(
            "It is certain",
            "It is decidedly so",
            "Without a doubt",
            "Yes definitely",
            "You may rely on it",
            "As I see it, yes",
            "Most likely",
            "Outlook good",
            "Yes",
            "Signs point to yes",
            "Don't count on it",
            "My reply is no",
            "My sources say no",
            "Outlook not so good",
            "Very doubtful",
            "Better not tell you now");

    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        var rand = ThreadLocalRandom.current();
        return new MessageContentBuilder().setText(outcomes.get(rand.nextInt(outcomes.size()))).build();
    }
}
