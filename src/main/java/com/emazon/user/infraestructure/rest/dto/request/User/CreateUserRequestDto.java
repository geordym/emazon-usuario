package com.emazon.user.infraestructure.rest.dto.request.User;


import com.emazon.user.infraestructure.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequestDto {

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name should not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name should not exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Identity document is mandatory")
    @Pattern(regexp = "\\d+", message = "Identity document must be numeric")
    private String identityDocument;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "Phone number must be a valid format and not exceed 13 characters")
    private String phoneNumber;

    @NotNull(message = "Birth date is mandatory")
    private LocalDate birthDate;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email should not exceed 100 characters")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;

    @Schema(description = "Role ID for the user. Possible values are: " +
            "1 (AUX_BODEGA), 2 (USER), 3 (ADMINISTRATOR)",
            required = true,
            allowableValues = {"1","2", "3"},
            example = "1",
            type = "integer")
    private Long id_rol;


}