package me.ericgi231.handler;

import me.ericgi231.dataType.MessageContent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;

import static me.ericgi231.constant.ActionStringMethodConstantMap.ACTION_STRING_METHOD_MAP;

public class MessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    private static final String LOG_KEYWORD_HIT = "Hit keyword [{}], remaining words {}";
    private static final String LOG_MESSAGE_CREATOR_INVALID = "Unable to create message";

    private static final String MESSAGE_CREATE_BUILDER_INVALID_MESSAGE = "Something went very wrong, I don't feel so good :(";

    private final Message message;
    private final ArrayList<String> words;

    public MessageHandler(Message m, String[] c) {
        this.message = m;
        this.words = new ArrayList<>(Arrays.asList(c));
        DetermineAction();
    }

    private void DetermineAction() {
        if (words.isEmpty()) {
            return;
        }

        var word = words.removeFirst();

        var method = ACTION_STRING_METHOD_MAP.get(word);
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
            assert content.text() != null;
            messageCreator.setContent(content.text());
        }
        if (content.hasEmbeds()) {
            assert content.embeds() != null;
            messageCreator.setEmbeds(content.embeds());
        }
        if (content.hasFiles()) {
            assert content.files() != null;
            messageCreator.setFiles(content.files());
        }

        if (messageCreator.isValid()) {
            message.getChannel().sendMessage(messageCreator.build()).setMessageReference(message).queue();
        } else {
            logger.error(LOG_MESSAGE_CREATOR_INVALID);
            message.reply(MESSAGE_CREATE_BUILDER_INVALID_MESSAGE).queue();
        }
    }
}
