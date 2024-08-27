package com.emazon.usuario.domain.exception.User;

public class PasswordFormatException extends RuntimeException {
    public PasswordFormatException(String message) {
        super(message);
    }
}