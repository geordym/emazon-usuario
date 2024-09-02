package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException() {
        super(ErrorMessages.CLIENT_NOT_FOUND);
    }
}