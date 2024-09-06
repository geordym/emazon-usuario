package com.emazon.user.application.dto.rest.dto.request.authentication;

public class AuthenticationValidationConstants {
    public static final String USERNAME_MANDATORY = "Username is mandatory";
    public static final String USERNAME_SIZE = "Username should not exceed 50 characters";
    public static final String PASSWORD_MANDATORY = "Password is mandatory";
    public static final String PASSWORD_SIZE = "Password should be at least 8 characters long";


    public static final int USERNAME_MAX_SIZE = 50;
    public static final int PASSWORD_MIN_SIZE = 8;
}
