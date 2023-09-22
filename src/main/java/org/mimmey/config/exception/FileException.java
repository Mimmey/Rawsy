package org.mimmey.config.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class FileException extends RuntimeException {

    public FileException(String message) {
        super(message);
    }
}
