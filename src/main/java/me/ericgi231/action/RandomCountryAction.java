package me.ericgi231.action;

import com.github.javafaker.Faker;
import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

public class RandomCountryAction {
    public static MessageContent action(final ArrayList<String> ignoredWords) {
        return new MessageContentBuilder().setText(new Faker().country().name()).build();
    }
}
