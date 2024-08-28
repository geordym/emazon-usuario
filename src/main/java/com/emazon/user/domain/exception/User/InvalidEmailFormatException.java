package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException() {
        super(ErrorMessages.INVALID_EMAIL_FORMAT);
    }
}