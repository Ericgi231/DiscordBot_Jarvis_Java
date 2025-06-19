package me.ericgi231.constant;
import me.ericgi231.action.HelloAction;
import me.ericgi231.action.InvalidAction;
import me.ericgi231.dataType.MessageContent;

import java.util.*;
import java.util.function.Function;

public final class ActionStringMethodConstantMap {
    public static final HashMap<String, Function<ArrayList<String>, MessageContent>> ACTION_STRING_METHOD_MAP = new HashMap<>(){
        {
            List.of("hello", "hi").forEach(s -> put(s, HelloAction::Action));
            List.of("help").forEach(s -> put(s, InvalidAction::Action));
            List.of("thank", "thanks").forEach(s -> put(s, InvalidAction::Action));
            List.of("what").forEach(s -> put(s, InvalidAction::Action));
            List.of("when").forEach(s -> put(s, InvalidAction::Action));
            List.of("google", "search").forEach(s -> put(s, InvalidAction::Action));
            List.of("cancel").forEach(s -> put(s, InvalidAction::Action));
            List.of("wiki").forEach(s -> put(s, InvalidAction::Action));
            List.of("man").forEach(s -> put(s, InvalidAction::Action));
            List.of("show", "post").forEach(s -> put(s, InvalidAction::Action));
            List.of("meme", "memes").forEach(s -> put(s, InvalidAction::Action));
            List.of("porn", "porns").forEach(s -> put(s, InvalidAction::Action));
            List.of("will", "am", "can", "is", "are", "would", "should", "do", "did").forEach(s -> put(s, InvalidAction::Action));
            List.of("many", "quantity", "number").forEach(s -> put(s, InvalidAction::Action));
        }
    };
}
