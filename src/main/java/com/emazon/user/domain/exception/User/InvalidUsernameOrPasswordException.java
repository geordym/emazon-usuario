package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException() {
        super(ErrorMessages.INVALID_USERNAME_PASSWORD_EXCEPTION);
    }
}