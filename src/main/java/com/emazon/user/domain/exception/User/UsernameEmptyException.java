package com.emazon.user.domain.exception.User;

public class UsernameEmptyException extends RuntimeException {
    public UsernameEmptyException(String message) {
        super(message);
    }
}
