package com.emazon.user.domain.exception.User;

public class InvalidIdentityDocumentException extends RuntimeException {
    public InvalidIdentityDocumentException(String message) {
        super(message);
    }
}