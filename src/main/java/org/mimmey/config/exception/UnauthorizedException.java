package org.mimmey.config.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
