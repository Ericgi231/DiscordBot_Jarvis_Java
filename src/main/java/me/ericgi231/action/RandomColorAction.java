package me.ericgi231.action;

import com.github.javafaker.Faker;
import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

public class RandomColorAction {
    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        var color = new Faker().color();
        return new MessageContentBuilder().setText(color.name() + " " + color.hex()).build();
    }
}
