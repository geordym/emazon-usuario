package com.emazon.user.domain.exception.Role;

import com.emazon.user.domain.util.ErrorMessages;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long roleId) {
        super(String.format(ErrorMessages.ROLE_NOT_FOUND, roleId));
    }
}
