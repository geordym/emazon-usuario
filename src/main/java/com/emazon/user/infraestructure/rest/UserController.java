package com.emazon.user.infraestructure.rest;


import com.emazon.user.application.services.IUserService;
import com.emazon.user.infraestructure.mapper.UserMapper;
import com.emazon.user.infraestructure.rest.dto.request.User.*;
import com.emazon.user.infraestructure.rest.dto.response.user.UserInfoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;


    @GetMapping("/info")
    public ResponseEntity<UserInfoResponseDto> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            UserInfoResponseDto userInfo = new UserInfoResponseDto(userDetails.getUsername(),
                    roles);
            return new ResponseEntity<>(userInfo,HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(null);
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

        userService.createUser(UserMapper.dtoToDomain(createUserRequestDto));
        return new ResponseEntity<>("User created succesfully", HttpStatus.CREATED);
    }




}
