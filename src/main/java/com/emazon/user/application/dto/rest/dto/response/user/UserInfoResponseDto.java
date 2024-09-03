package com.emazon.user.application.dto.rest.dto.response.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfoResponseDto {
    public Long idUser;
    public String username;
    private List<String> roles;
    public UserInfoResponseDto(Long idUser, String username, List<String> roles) {
        this.idUser = idUser;
        this.username = username;
        this.roles = roles;
    }

    // Getters y Setters
}
