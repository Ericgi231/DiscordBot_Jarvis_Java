package me.ericgi231.dataType;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public record MessageContent(List<MessageEmbed> embeds, List<FileUpload> files, String text) {

    @Override
    @Nullable
    public List<MessageEmbed> embeds() {
        return embeds;
    }

    @Override
    @Nullable
    public List<FileUpload> files() {
        return files;
    }

    @Override
    @Nullable
    public String text() {
        return text;
    }

    public boolean hasEmbeds() {
        return embeds != null;
    }

    public boolean hasFiles() {
        return files != null;
    }

    public boolean hasText() {
        return text != null;
    }
}
