package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;

public class UnderageUserException extends RuntimeException {
    public UnderageUserException() {
        super(ErrorMessages.UNDERAGE_USER);
    }
}
