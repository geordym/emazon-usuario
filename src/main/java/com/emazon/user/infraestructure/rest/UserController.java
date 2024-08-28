package com.emazon.user.infraestructure.rest;


import com.emazon.user.application.services.UserService;
import com.emazon.user.infraestructure.mapper.UserMapper;
import com.emazon.user.infraestructure.rest.dto.request.user.CreateUserRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Register a New User",
            description = "This endpoint allows the registration of a new user by providing all the necessary details such as personal information, contact details, and the associated role. " +
                    "Ensure that all mandatory fields are filled correctly. A successful registration returns a confirmation message."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "409", description = "User email or identityDocument exists in bd", content = @Content)
    })
    @PostMapping
    public ResponseEntity<String> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Complete user details required for registration",
                    required = true, content = @Content(schema = @Schema(implementation = CreateUserRequestDto.class)))
            @RequestBody @Valid CreateUserRequestDto createUserRequestDto) {

        userService.createUser(UserMapper.dtoToDomain(createUserRequestDto));
        return new ResponseEntity<>("User created succesfully", HttpStatus.CREATED);
    }

}
