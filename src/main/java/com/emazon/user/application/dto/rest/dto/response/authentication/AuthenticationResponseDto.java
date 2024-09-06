package com.emazon.user.application.dto.rest.dto.response.authentication;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponseDto {
    private final String jwt;
}