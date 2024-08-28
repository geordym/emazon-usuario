package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;

public class PasswordFormatException extends RuntimeException {
    public PasswordFormatException() {
        super(ErrorMessages.PASSWORD_FORMAT);
    }
}