package com.emazon.user.infraestructure.exceptions;

import com.emazon.user.domain.util.ErrorMessages;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super(ErrorMessages.INVALID_CREDENTIALS_EXCEPTION);
    }
}