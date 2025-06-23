package me.ericgi231.helper;

import me.ericgi231.dataType.ImageQueryData;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static me.ericgi231.constant.PathConstant.PATH_TO_COLLECTION;
import static me.ericgi231.helper.GlobalHelper.cleanString;

public final class ImageQueryHelper {
    private static final Logger logger = LoggerFactory.getLogger(ImageQueryHelper.class);

    private static final long MAX_SIZE = 8000000;
    private static final long MAX_FAILS = 20;

    private ImageQueryHelper() {}

    public static List<FileUpload> getStockImages(ImageQueryData imageQueryData) {
        return List.of();

    }

    public static List<FileUpload> getPornImages(ImageQueryData imageQueryData) {
        return List.of();
    }

    @NotNull
    public static List<FileUpload> getMemeImages(@NotNull ImageQueryData imageQueryData) {
        final var collection = new File(PATH_TO_COLLECTION);
        final File[] files;
        if (imageQueryData.hasTerms()) {
            assert imageQueryData.terms() != null;
            files = collection.listFiles((dir, name) -> cleanString(name)
                    .contains(cleanString(String.join("", imageQueryData.terms()))));
        } else {
            files = collection.listFiles();
        }
        if (files == null || files.length == 0) {
            return List.of();
        } else {
            return getRandomFilesFromFileArray(files, imageQueryData.quantity());
        }
    }

    //TODO Improve logic for handling oversized files
    private static List<FileUpload> getRandomFilesFromFileArray(File[] files, int count) {
        final var rand = ThreadLocalRandom.current();
        var failedAttempts = 0;
        final var images = new ArrayList<FileUpload>();
        logger.info("{}", rand.nextInt(files.length+1));
        if (files.length != 0) {
            for (int i = 0; i < count && failedAttempts < MAX_FAILS; i++) {
                var index = rand.nextInt(files.length);
                var file = files[index];
                if (file.length() < MAX_SIZE) {
                    images.add(FileUpload.fromData(file, file.getName()));
                } else {
                    failedAttempts++;
                    i--;
                }
            }
        }
        return images;
    }
}
