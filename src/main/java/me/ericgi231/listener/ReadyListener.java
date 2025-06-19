package me.ericgi231.listener;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadyListener implements EventListener {
    private static final Logger logger = LoggerFactory.getLogger(ReadyListener.class);
    private static final String LOG_ONLINE = "Jarvis online, ready to tease and please ;)";

    @Override
    public void onEvent(@NotNull GenericEvent event)
    {
        if (event instanceof ReadyEvent) {
            logger.info(LOG_ONLINE);
        }
    }
}
