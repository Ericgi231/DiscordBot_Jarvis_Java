package me.ericgi231.listener;

import me.ericgi231.handler.MessageHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class MessageListener extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
    private static final String LOG_RESPONDING_TO_MESSAGE = "Responding to message {} by {} in {}";
    private static final String LOG_RESPONDED_TO_MESSAGE = "Responded to message {} by {} in {}";

    private static final String EXCEPTION_MESSAGE = "Something went wrong :( ";
    private static final List<String> BOT_NAMES = List.of("jarvis", "edi", "jarv");

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;

        var message = event.getMessage();
        var content = message.getContentRaw().toLowerCase().split(" ");

        if (BOT_NAMES.contains(content[0]))
        {
            logger.info(LOG_RESPONDING_TO_MESSAGE,
                    Arrays.toString(content),
                    event.getAuthor().getName(),
                    event.getChannel().getName());
            try {
                new MessageHandler(message, content);
            } catch (Exception e) {
                logger.error(e.toString());
                message.reply(EXCEPTION_MESSAGE).queue();
            }
            logger.info(LOG_RESPONDED_TO_MESSAGE,
                    Arrays.toString(content),
                    event.getAuthor().getName(),
                    event.getChannel().getName());
        }
    }
}
