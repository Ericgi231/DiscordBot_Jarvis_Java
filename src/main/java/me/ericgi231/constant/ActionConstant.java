package me.ericgi231.constant;
import me.ericgi231.action.*;
import me.ericgi231.dataType.ImageQueryData;
import me.ericgi231.dataType.MessageContent;
import me.ericgi231.helper.ImageQueryHelper;
import net.dv8tion.jda.api.utils.FileUpload;

import java.util.*;
import java.util.function.Function;

public final class ActionConstant {
    public enum ImageType {
        PORN,
        MEME,
        STOCK
    }

    public static final HashMap<String, ImageType> STRING_IMAGE_TYPE_MAP = new HashMap<>() {
        {
            List.of("meme", "memes").forEach(s -> put(s, ImageType.MEME));
            List.of("porn", "porns").forEach(s -> put(s, ImageType.PORN));
            List.of("stock", "image", "images").forEach(s -> put(s, ImageType.STOCK));
        }
    };

    public static final HashMap<ImageType, Function<ImageQueryData, List<FileUpload>>> IMAGE_TYPE_FUNCTION_MAP = new HashMap<>(){
        {
            put(ImageType.MEME, ImageQueryHelper::getMemeImages);
            put(ImageType.PORN, ImageQueryHelper::getPornImages);
            put(ImageType.STOCK, ImageQueryHelper::getStockImages);
        }
    };

    public static final HashMap<String, Function<ArrayList<String>, MessageContent>> STRING_ACTION_FUNCTION_MAP = new HashMap<>(){
        {
            List.of("hello", "hi").forEach(s -> put(s, HelloMessageAction::action));
            List.of("help").forEach(s -> put(s, HelpMessageAction::action));
            List.of("thank", "thanks").forEach(s -> put(s, ThanksMessageAction::action));
            List.of("who", "whome").forEach(s -> put(s, RandomHumanNameAction::action));
            List.of("how").forEach(s -> put(s, RandomWordsAction::action));
            List.of("when").forEach(s -> put(s, RandomDateTimeAction::action));
            List.of("google", "search", "duckduck", "duckduckgo").forEach(s -> put(s, DuckDuckGoSearchAction::action));
            List.of("cancel", "farm").forEach(s -> put(s, RandomEmojisAction::action));
            List.of("wiki").forEach(s -> put(s, WikiAction::action));
            List.of("show", "post").forEach(s -> put(s, ImagesAction::action));
            List.of("will", "am", "can", "are", "would", "do", "did").forEach(s -> put(s, RandomEightBallAction::action));
            List.of("many", "quantity", "number", "much").forEach(s -> put(s, RandomNumberAction::action));
            List.of("where", "location").forEach(s -> put(s, RandomCountryAction::action));
            List.of("color", "colour", "shade").forEach(s -> put(s, RandomColorAction::action));
            List.of("music", "listen").forEach(s -> put(s, RandomMusicAction::action));
            List.of("meal", "dinner", "lunch", "breakfast", "food", "eat").forEach(s -> put(s, RandomFoodAction::action));
            List.of("test1").forEach(s -> put(s, TestAction::action));
        }
    };

    public static final Function<ArrayList<String>, MessageContent> DEFAULT_ACTION = RandomWordsAction::action;
    public static final ImageType DEFAULT_IMAGE_TYPE = ImageType.STOCK;
}
