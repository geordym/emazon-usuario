package com.emazon.user.application.dto.rest.dto.request.authentication;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequestDto {
    @NotBlank(message = AuthenticationValidationConstants.USERNAME_MANDATORY)
    @Size(max = AuthenticationValidationConstants.USERNAME_MAX_SIZE, message = AuthenticationValidationConstants.USERNAME_SIZE)
    private String username;

    @NotBlank(message = AuthenticationValidationConstants.PASSWORD_MANDATORY)
    @Size(min = AuthenticationValidationConstants.PASSWORD_MIN_SIZE, message = AuthenticationValidationConstants.PASSWORD_SIZE)
    private String password;
}
