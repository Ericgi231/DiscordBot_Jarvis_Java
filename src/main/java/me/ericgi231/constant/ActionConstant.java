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
            List.of("hello", "hi").forEach(s -> put(s, HelloMessageAction::Action));
            List.of("help").forEach(s -> put(s, HelpMessageAction::Action));
            List.of("thank", "thanks").forEach(s -> put(s, ThanksMessageAction::Action));
            List.of("who", "whome").forEach(s -> put(s, RandomHumanNameAction::Action));
            List.of("how").forEach(s -> put(s, RandomWordsAction::Action));
            List.of("when").forEach(s -> put(s, RandomDateTimeAction::Action));
            List.of("google", "search", "duckduck", "duckduckgo").forEach(s -> put(s, DuckDuckGoSearchAction::Action));
            List.of("cancel", "farm").forEach(s -> put(s, RandomEmojisAction::Action));
            List.of("wiki").forEach(s -> put(s, WikiAction::Action));
            List.of("show", "post").forEach(s -> put(s, ImagesAction::Action));
            List.of("will", "am", "can", "are", "would", "do", "did").forEach(s -> put(s, RandomEightBallAction::Action));
            List.of("many", "quantity", "number", "much").forEach(s -> put(s, RandomNumberAction::Action));
            List.of("where", "location").forEach(s -> put(s, RandomCountryAction::Action));
            List.of("color", "colour", "shade").forEach(s -> put(s, RandomColorAction::Action));
            List.of("music", "listen").forEach(s -> put(s, RandomMusicAction::Action));
            List.of("meal", "dinner", "lunch", "breakfast", "food", "eat").forEach(s -> put(s, RandomFoodAction::Action));
        }
    };

    public static final Function<ArrayList<String>, MessageContent> DEFAULT_ACTION = RandomWordsAction::Action;
    public static final ImageType DEFAULT_IMAGE_TYPE = ImageType.STOCK;
}
