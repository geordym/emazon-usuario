package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;

public class IdentityDocumentTakenException extends RuntimeException {
    public IdentityDocumentTakenException() {
        super(ErrorMessages.INVALID_IDENTITY_DOCUMENT);
    }
}