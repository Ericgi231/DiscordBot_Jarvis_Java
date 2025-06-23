package me.ericgi231.dataType;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageContentBuilder {
    private List<MessageEmbed> embeds;
    private List<FileUpload> files;
    private String text;

    @NotNull
    public MessageContent build() {
        return new MessageContent(embeds, files, text);
    }

    @NotNull
    public MessageContentBuilder addEmbed(@NotNull MessageEmbed embed) {
        this.embeds.add(embed);
        return this;
    }

    @NotNull
    public MessageContentBuilder setEmbeds(@NotNull MessageEmbed embed) {
        this.embeds = List.of(embed);
        return this;
    }

    @NotNull
    public MessageContentBuilder setEmbeds(@NotNull List<MessageEmbed> embeds) {
        this.embeds = embeds;
        return this;
    }

    @NotNull
    public MessageContentBuilder addFile(@NotNull FileUpload file) {
        this.files.add(file);
        return this;
    }

    @NotNull
    public MessageContentBuilder setFiles(@NotNull FileUpload file) {
        this.files = List.of(file);
        return this;
    }

    @NotNull
    public MessageContentBuilder setFiles(@NotNull List<FileUpload> files) {
        this.files = files;
        return this;
    }

    @NotNull
    public MessageContentBuilder setText(@NotNull String text) {
        this.text = text;
        return this;
    }
}
