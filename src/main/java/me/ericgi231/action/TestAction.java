package me.ericgi231.action;

import me.ericgi231.dataType.MessageContent;
import me.ericgi231.dataType.MessageContentBuilder;

import java.util.ArrayList;

public class TestAction {
    public static MessageContent action(final ArrayList<String> ignoredWords){
        var builder = new MessageContentBuilder().setText("https://images.unsplash.com/photo-1748324575258-b51559c5fefd https://images.unsplash.com/photo-1748324575258-b51559c5fefd");
        return builder.build();
    }
}
