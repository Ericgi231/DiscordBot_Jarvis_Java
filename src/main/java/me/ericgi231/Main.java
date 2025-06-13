package me.ericgi231;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception
    {
        System.out.print("Hello world");
        EnumSet<GatewayIntent> privilegedIntents = GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS);
        JDABuilder.createDefault(args[0], privilegedIntents)
                .addEventListeners(new MyListener())
                .build();
    }
}