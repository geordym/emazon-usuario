package com.emazon.user.application.dto.rest.dto.request.user;

public class CreateUserValidationConstants {
    public static final int FIRST_NAME_MAX_SIZE = 50;
    public static final int LAST_NAME_MAX_SIZE = 50;
    public static final int EMAIL_MAX_SIZE = 100;
    public static final int PASSWORD_MIN_SIZE = 8;

    // Expresiones regulares
    public static final String PHONE_NUMBER_REGEX = "^\\+?\\d{1,13}$";
    public static final String IDENTITY_DOCUMENT_REGEX = "\\d+";

    // Mensajes de validación
    public static final String FIRST_NAME_MANDATORY = "First name is mandatory";
    public static final String FIRST_NAME_SIZE = "First name should not exceed " + FIRST_NAME_MAX_SIZE + " characters";

    public static final String LAST_NAME_MANDATORY = "Last name is mandatory";
    public static final String LAST_NAME_SIZE = "Last name should not exceed " + LAST_NAME_MAX_SIZE + " characters";

    public static final String IDENTITY_DOCUMENT_MANDATORY = "Identity document is mandatory";
    public static final String IDENTITY_DOCUMENT_PATTERN = "Identity document must be numeric";

    public static final String PHONE_NUMBER_MANDATORY = "Phone number is mandatory";
    public static final String PHONE_NUMBER_PATTERN = "Phone number must be a valid format and not exceed 13 characters";

    public static final String BIRTH_DATE_MANDATORY = "Birth date is mandatory";

    public static final String EMAIL_MANDATORY = "Email is mandatory";
    public static final String EMAIL_INVALID = "Email should be valid";
    public static final String EMAIL_SIZE = "Email should not exceed " + EMAIL_MAX_SIZE + " characters";

    public static final String PASSWORD_MANDATORY = "Password is mandatory";
    public static final String PASSWORD_SIZE = "Password should be at least " + PASSWORD_MIN_SIZE + " characters long";


}
