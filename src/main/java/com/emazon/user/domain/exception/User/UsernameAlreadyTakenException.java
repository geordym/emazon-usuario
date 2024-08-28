package com.emazon.user.domain.exception.User;

public class UsernameAlreadyTakenException extends RuntimeException{

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }

}
