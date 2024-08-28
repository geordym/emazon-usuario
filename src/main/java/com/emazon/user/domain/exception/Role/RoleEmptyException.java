package com.emazon.usuario.domain.exception.Role;

public class RoleEmptyException extends RuntimeException {
    public RoleEmptyException(String message) {
        super(message);
    }
}