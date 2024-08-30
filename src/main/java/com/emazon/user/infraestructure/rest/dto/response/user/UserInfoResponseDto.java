package com.emazon.user.infraestructure.rest.dto.response.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfoResponseDto {
    public String username;
    private List<String> roles;
    public UserInfoResponseDto(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    // Getters y Setters
}
