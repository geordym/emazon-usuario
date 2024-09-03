package com.emazon.user.infraestructure.rest;


import com.emazon.user.application.dto.rest.dto.request.user.CreateUserRequestDto;
import com.emazon.user.application.services.IUserService;
import com.emazon.user.application.dto.rest.dto.response.user.UserInfoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/info/client/{clientId}")
    public ResponseEntity<UserInfoResponseDto> getClientInfo(@PathVariable("clientId") Long clientId) {
        UserInfoResponseDto userInfo = userService.getClientInfoById(clientId);
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoResponseDto> getUserInfoByAuthenticationContext() {
        UserInfoResponseDto userInfoResponseDto = userService.getUserInfoByAuthenticationContext();
        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoResponseDto);
    }


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

        String response = userService.createUser(createUserRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }




}
