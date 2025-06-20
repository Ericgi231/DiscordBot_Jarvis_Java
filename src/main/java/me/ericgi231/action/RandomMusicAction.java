package me.ericgi231.action;

import com.github.javafaker.Faker;
import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

public class RandomMusicAction {
    public static MessageContent Action(final ArrayList<String> ignoredWords) {
        var music = new Faker().music();
        return new MessageContentBuilder().setText(music.genre()).build();
    }
}
