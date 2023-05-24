package org.mimmey.utils;

import lombok.AllArgsConstructor;
import org.mimmey.config.exception.FileException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.BiConsumer;

@AllArgsConstructor
public enum FileWriterTypes {

    AUDIO((path, content) -> {
        File file = new File(path);

        ByteArrayInputStream bais = new ByteArrayInputStream(content);

        try (AudioInputStream ais = AudioSystem.getAudioInputStream(bais)) {
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);
        } catch (IOException | UnsupportedAudioFileException e) {
            throw new FileException();
        }
    }),

    ZIP((path, content) -> {
        try {
            Files.write(Paths.get(path), content);
        } catch (IOException e) {
            throw new FileException();
        }
    }),

    IMAGE((path, content) -> {
        try {
            Files.write(Paths.get(path), content);
        } catch (IOException e) {
            throw new FileException();
        }
    });

    private final BiConsumer<String, byte[]> writer;

    public void writeToFile(String path, byte[] content) {
        this.writer.accept(path, content);
    }
}
