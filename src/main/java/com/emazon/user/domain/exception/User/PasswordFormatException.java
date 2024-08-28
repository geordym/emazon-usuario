package com.emazon.user.domain.exception.User;

public class PasswordFormatException extends RuntimeException {
    public PasswordFormatException(String message) {
        super(message);
    }
}