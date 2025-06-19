package me.ericgi231.helper;

import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;

public final class ActionHelper {
    private final static String COLLECTION_PATH = System.getenv("COLLECTION");

    private ActionHelper() {}

    public static FileUpload GetLocalFile(String fileName) {
        return FileUpload.fromData(new File(COLLECTION_PATH + fileName), fileName);
    }
}
