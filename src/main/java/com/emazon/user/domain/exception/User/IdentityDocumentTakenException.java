package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;

public class IdentityDocumentTakenException extends RuntimeException {
    public IdentityDocumentTakenException() {
        super(ErrorMessages.IDENTITY_DOCUMENT_ALREADY_TAKEN);
    }
}