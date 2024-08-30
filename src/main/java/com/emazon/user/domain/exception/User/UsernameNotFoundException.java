package com.emazon.user.domain.exception.User;

import com.emazon.user.domain.util.ErrorMessages;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String username) {
        super(String.format(ErrorMessages.USERNAME_NOT_FOUND, username));
    }

}
