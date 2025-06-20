package me.ericgi231.action;

import com.github.javafaker.Faker;
import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

public class RandomHumanNameAction {
    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        return new MessageContentBuilder().setText(new Faker().name().fullName()).build();
    }
}
