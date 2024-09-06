package com.emazon.user.application.dto.rest.dto.request.user;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {
    @NotBlank(message = CreateUserValidationConstants.FIRST_NAME_MANDATORY)
    @Size(max = CreateUserValidationConstants.FIRST_NAME_MAX_SIZE, message = CreateUserValidationConstants.FIRST_NAME_SIZE)
    private String firstName;

    @NotBlank(message = CreateUserValidationConstants.LAST_NAME_MANDATORY)
    @Size(max = CreateUserValidationConstants.LAST_NAME_MAX_SIZE, message = CreateUserValidationConstants.LAST_NAME_SIZE)
    private String lastName;

    @NotBlank(message = CreateUserValidationConstants.IDENTITY_DOCUMENT_MANDATORY)
    @Pattern(regexp = CreateUserValidationConstants.IDENTITY_DOCUMENT_REGEX, message = CreateUserValidationConstants.IDENTITY_DOCUMENT_PATTERN)
    private String identityDocument;

    @NotBlank(message = CreateUserValidationConstants.PHONE_NUMBER_MANDATORY)
    @Pattern(regexp = CreateUserValidationConstants.PHONE_NUMBER_REGEX, message = CreateUserValidationConstants.PHONE_NUMBER_PATTERN)
    private String phoneNumber;

    @NotNull(message = CreateUserValidationConstants.BIRTH_DATE_MANDATORY)
    private LocalDate birthDate;

    @NotBlank(message = CreateUserValidationConstants.EMAIL_MANDATORY)
    @Email(message = CreateUserValidationConstants.EMAIL_INVALID)
    @Size(max = CreateUserValidationConstants.EMAIL_MAX_SIZE, message = CreateUserValidationConstants.EMAIL_SIZE)
    private String email;

    @NotBlank(message = CreateUserValidationConstants.PASSWORD_MANDATORY)
    @Size(min = CreateUserValidationConstants.PASSWORD_MIN_SIZE, message = CreateUserValidationConstants.PASSWORD_SIZE)
    private String password;

}