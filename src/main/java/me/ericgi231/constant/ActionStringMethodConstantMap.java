package me.ericgi231.constant;
import me.ericgi231.action.*;
import me.ericgi231.dataType.MessageContent;

import java.util.*;
import java.util.function.Function;

public final class ActionStringMethodConstantMap {
    public static final HashMap<String, Function<ArrayList<String>, MessageContent>> ACTION_STRING_METHOD_MAP = new HashMap<>(){
        {
            List.of("hello", "hi").forEach(s -> put(s, HelloMessageAction::Action));
            List.of("help").forEach(s -> put(s, HelpMessageAction::Action));
            List.of("thank", "thanks").forEach(s -> put(s, ThanksMessageAction::Action));
            List.of("who", "whome").forEach(s -> put(s, RandomHumanNameAction::Action));
            List.of("how").forEach(s -> put(s, RandomWordsAction::Action));
            List.of("when").forEach(s -> put(s, RandomDateTimeAction::Action));
            List.of("google", "search").forEach(s -> put(s, InvalidMessageAction::Action));
            List.of("cancel", "farm").forEach(s -> put(s, RandomEmojisAction::Action));
            List.of("wiki").forEach(s -> put(s, InvalidMessageAction::Action));
            List.of("man").forEach(s -> put(s, InvalidMessageAction::Action));
            List.of("show", "post").forEach(s -> put(s, InvalidMessageAction::Action));
            List.of("meme", "memes").forEach(s -> put(s, InvalidMessageAction::Action));
            List.of("porn", "porns").forEach(s -> put(s, InvalidMessageAction::Action));
            List.of("will", "am", "can", "is", "are", "would", "should", "do", "did").forEach(s -> put(s, RandomEightBallAction::Action));
            List.of("many", "quantity", "number", "much").forEach(s -> put(s, RandomNumberAction::Action));
            List.of("where", "location").forEach(s -> put(s, RandomCountryAction::Action));
            List.of("color", "colour", "shade").forEach(s -> put(s, RandomColorAction::Action));
        }
    };
}
