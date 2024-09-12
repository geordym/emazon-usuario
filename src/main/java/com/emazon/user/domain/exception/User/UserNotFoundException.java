package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ErrorMessages.USER_NOT_FOUND);
    }
}