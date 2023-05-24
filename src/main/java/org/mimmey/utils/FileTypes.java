package org.mimmey.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
public enum FileTypes {

    AVATAR(FileWorker.DATA_PATH + File.separator + "avatar" + File.separator + "default.jpg",
            FileWriterTypes.IMAGE),
    JINGLE(FileWorker.DATA_PATH + File.separator + "jingle" + File.separator + "default.wav",
            FileWriterTypes.AUDIO),
    PREVIEW(FileWorker.DATA_PATH + File.separator + "preview" + File.separator + "default.wav",
            FileWriterTypes.AUDIO),
    ARCHIVE(FileWorker.DATA_PATH + File.separator + "jingle" + File.separator + "default.wav",
            FileWriterTypes.ZIP);

    @Getter
    private final String pathToDefault;
    private final FileWriterTypes fileWriterType;

    public void writeToFile(String path, byte[] content) {
        this.fileWriterType.writeToFile(path, content);
    }
}
