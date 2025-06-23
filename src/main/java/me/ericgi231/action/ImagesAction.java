package me.ericgi231.action;

import me.ericgi231.dataType.ImageQueryDataBuilder;
import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

import static me.ericgi231.constant.ActionConstant.*;

public class ImagesAction {
    private static final Logger logger = LoggerFactory.getLogger(ImagesAction.class);

    private static final String ERROR_MESSAGE = "No valid images found.";
    //TODO implement numeric words
    private static final HashMap<String, Integer> WORD_NUMBERS = new HashMap<>() {
        {
            put("one", 1);
            put("two", 2);
            put("three", 3);
            put("four", 4);
            put("five", 5);
            put("six", 6);
            put("seven", 7);
            put("eight", 8);
            put("nine", 9);
            put("ten", 10);
        }
    };

    public static MessageContent Action(final ArrayList<String> words) {
        var builder = new ImageQueryDataBuilder();

        if (words.getFirst().matches("\\d+")) {
            builder.setQuantity(Integer.parseInt(words.removeFirst()));
        }

        if (STRING_IMAGE_TYPE_MAP.containsKey(words.getLast())) {
            builder.setImageType(STRING_IMAGE_TYPE_MAP.get(words.removeLast()));
        } else {
            builder.setImageType(DEFAULT_IMAGE_TYPE);
        }

        for (String word : words) {
            builder.addTerm(word);
        }

        var data = builder.build();

        var files = IMAGE_TYPE_FUNCTION_MAP.get(data.imageType()).apply(data);
        var error = "";
        if (files == null || files.isEmpty()) {
            error = ERROR_MESSAGE;
        }

        //TODO rework code to remove assertions across app
        assert files != null;
        return new MessageContentBuilder().setFiles(files).setText(error).build();
    }
}
