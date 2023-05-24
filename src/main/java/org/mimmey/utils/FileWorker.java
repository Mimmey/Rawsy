package org.mimmey.utils;

import org.mimmey.config.exception.FileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWorker {

    public final static String DATA_PATH = ".." + File.separator + "rawsy_data";


    public static String getAvatarPath(long userId) {
        return DATA_PATH + File.separator + "avatar" + File.separator + userId + ".jpg";
    }

    public static String getJinglePath(long userId) {
        return DATA_PATH + File.separator + "jingle" + File.separator + userId + ".wav";
    }

    public static String getArchivePath(long trackId) {
        return DATA_PATH + File.separator + "archive" + File.separator + trackId + ".zip";

    }

    public static String getPreviewPath(long trackId) {
        return DATA_PATH + File.separator + "preview" + File.separator + trackId + ".wav";
    }

    public static void tryCreateDefault(String targetPath, FileTypes fileType) {
        tryWriteToFile(targetPath, tryReadFile(fileType.getPathToDefault()), fileType);
    }

    public static byte[] tryReadFile(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new FileException();
        }
    }

    public static void tryWriteToFile(String targetPath, byte[] content, FileTypes fileType) {
        fileType.writeToFile(targetPath, content);
    }
}
