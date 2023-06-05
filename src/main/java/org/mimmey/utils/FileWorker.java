package org.mimmey.utils;

import org.mimmey.config.exception.FileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWorker {

    public final static String DATA_PATH = "." + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator + "data";
    public final static String AVATARS_PATH = DATA_PATH + File.separator + "avatar";
    public final static String JINGLES_PATH = DATA_PATH + File.separator + "jingle";
    public final static String ARCHIVE_PATH = DATA_PATH + File.separator + "archive";
    public final static String PREVIEW_PATH = DATA_PATH + File.separator + "preview";

    public static String getAvatarPath(long userId) {
        return getFilePath(AVATARS_PATH, String.valueOf(userId), ".jpg");
    }

    public static String getJinglePath(long userId) {
        return getFilePath(JINGLES_PATH, String.valueOf(userId), ".wav");
    }

    public static String getArchivePath(long trackId) {
        return getFilePath(ARCHIVE_PATH, String.valueOf(trackId), ".zip");
    }

    public static String getPreviewPath(long trackId) {
        return getFilePath(PREVIEW_PATH, String.valueOf(trackId), ".wav");
    }

    private static String getFilePath(String parentDirPath, String fileName, String extension) {
        return parentDirPath + File.separator + fileName + extension;
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
