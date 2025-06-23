package me.ericgi231.handler;

import me.ericgi231.dataType.MessageContent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

import static me.ericgi231.constant.ActionConstant.STRING_ACTION_FUNCTION_MAP;
import static me.ericgi231.constant.ActionConstant.DEFAULT_ACTION;

public class MessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    private static final String LOG_KEYWORD_HIT = "Hit keyword [{}], remaining words {}";
    private static final String LOG_MESSAGE_CREATOR_INVALID = "Unable to create message";
    private static final String MESSAGE_SENT_MESSAGE = "Sent message [{}]";

    private static final String MESSAGE_CREATE_BUILDER_INVALID_MESSAGE = "Something went very wrong, I don't feel so good :(";

    private final Message message;
    private final ArrayList<String> words;

    public MessageHandler(Message m, String[] c) {
        this.message = m;
        this.words = new ArrayList<>(Arrays.asList(c));
        DetermineAction();
    }

    //TODO Handle "what ___" better
    private void DetermineAction() {
        if (words.isEmpty()) {
            PostReply(DEFAULT_ACTION.apply(this.words));
            return;
        }

        var word = words.removeFirst();

        var method = STRING_ACTION_FUNCTION_MAP.get(word);
        if (method == null) {
            DetermineAction();
        } else {
            logger.info(LOG_KEYWORD_HIT, word, words);
            PostReply(method.apply(words));
        }
    }

    private void PostReply(MessageContent content) {
        var messageCreator = new MessageCreateBuilder();
        if (content.hasText()) {
            messageCreator.setContent(content.text());
        }
        if (content.hasEmbeds()) {
            //TODO solve why this is expecting an assertion, and cleanup assertions across codebase
            messageCreator.setEmbeds(content.embeds());
        }
        if (content.hasFiles()) {
            messageCreator.setFiles(content.files());
        }

        if (messageCreator.isValid()) {
            var responseCreateData = messageCreator.build();
            message.getChannel().sendMessage(responseCreateData).setMessageReference(message).queue();
            logger.info(MESSAGE_SENT_MESSAGE, responseCreateData.getContent());
        } else {
            message.reply(MESSAGE_CREATE_BUILDER_INVALID_MESSAGE).queue();
            logger.error(LOG_MESSAGE_CREATOR_INVALID);
        }
    }
}
