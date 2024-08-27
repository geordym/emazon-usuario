package com.emazon.usuario.domain.exception.User;

public class PasswordEmptyException extends RuntimeException {
    public PasswordEmptyException(String message) {
        super(message);
    }
}
