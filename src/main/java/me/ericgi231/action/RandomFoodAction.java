package me.ericgi231.action;

import com.github.javafaker.Faker;
import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

public class RandomFoodAction {
    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        var food = new Faker().food();
        return new MessageContentBuilder().setText(food.dish()).build();
    }
}
