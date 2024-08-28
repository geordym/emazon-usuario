package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;


public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException() {
        super(ErrorMessages.EMAIL_ALREADY_TAKEN);
    }
}