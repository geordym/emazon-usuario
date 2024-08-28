package com.emazon.user.domain.exception.Role;

public class RoleEmptyException extends RuntimeException {
    public RoleEmptyException(String message) {
        super(message);
    }
}