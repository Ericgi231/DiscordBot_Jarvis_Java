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

    private static final String NO_FILES_ERROR_MESSAGE = "No valid images found.";
    private static final String INVALID_QUANTITY_ERROR_MESSAGE = "I can only post up to 10 images.";
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

    public static MessageContent action(final ArrayList<String> words) {
        var queryBuilder = new ImageQueryDataBuilder();
        var messageBuilder = new MessageContentBuilder();

        //TODO clean up this special case
        if (!words.isEmpty() && words.getFirst().matches("me")) {
            words.removeFirst();
        }

        if (!words.isEmpty() && words.getFirst().matches("\\d+")) {
            var num = Integer.parseInt(words.removeFirst());
            if (num <= 0 || num > 10) {
                return messageBuilder.setText(INVALID_QUANTITY_ERROR_MESSAGE).build();
            }
            queryBuilder.setQuantity(num);
        }

        if (!words.isEmpty() && STRING_IMAGE_TYPE_MAP.containsKey(words.getLast())) {
            queryBuilder.setImageType(STRING_IMAGE_TYPE_MAP.get(words.removeLast()));
        }

        for (String word : words) {
            queryBuilder.addTerm(word);
        }

        var data = queryBuilder.build();
        var files = IMAGE_TYPE_FUNCTION_MAP.get(data.imageType()).apply(data);
        if (files == null || files.isEmpty()) {
            return messageBuilder.setText(NO_FILES_ERROR_MESSAGE).build();
        }
        return messageBuilder.setFiles(files).build();
    }
}
