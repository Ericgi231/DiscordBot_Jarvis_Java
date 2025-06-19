package me.ericgi231;

import me.ericgi231.listener.MessageListener;
import me.ericgi231.listener.ReadyListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception
    {
        EnumSet<GatewayIntent> privilegedIntents = GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS);
        JDABuilder.createDefault(args[0], privilegedIntents)
                .addEventListeners(new MessageListener())
                .addEventListeners(new ReadyListener())
                .build()
                .awaitReady();
    }
}

