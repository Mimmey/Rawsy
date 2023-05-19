package org.mimmey.config.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@RequiredArgsConstructor
public final class ExceptionController {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ExceptionResponse error(AccessDeniedException e) {
        String messageAccessDenied = "Доступ запрещен";
        String anotherAuthException = "Ошибка доступа";

        if (e.getMessage().toLowerCase().contains("access is denied")) {
            return new ExceptionResponse(messageAccessDenied);
        } else {
            return new ExceptionResponse(anotherAuthException);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ExceptionResponse error(EmptyResultDataAccessException e) {
        String message = "Не найдено";
        return new ExceptionResponse(message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionResponse error(EntityNotFoundException e) {
        String message = "Не найдено";
        return new ExceptionResponse(message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileException.class)
    public ExceptionResponse error(FileException e) {
        String message = "Ошибка работы с файлом";
        return new ExceptionResponse(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ExceptionResponse error(MissingServletRequestParameterException e) {
        String message = "Ошибка выполнения запроса";
        return new ExceptionResponse(message);
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionResponse error(HttpMessageNotReadableException e) {
        String message = "Неверный формат входящих значений";
        return new ExceptionResponse(message);
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(TransactionSystemException.class)
    public ExceptionResponse error(TransactionSystemException e) {
        String message = "Входящие значения не соответствуют предъявленным к ним требованиям";
        return new ExceptionResponse(message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ExceptionResponse error(MethodArgumentTypeMismatchException e) {
        String message = "Ошибка";
        return new ExceptionResponse(message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PersistenceException.class)
    public ExceptionResponse error(PersistenceException e) {
        String message = "Ошибка получения данных";
        return new ExceptionResponse(message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponse error(RuntimeException e) {
        String message = "Ошибка";
        return new ExceptionResponse(message);
    }
}
