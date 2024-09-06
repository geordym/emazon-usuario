package com.emazon.user.application.dto.rest.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoResponseDto {
    public Long idUser;
    public String username;
    private List<String> roles;
}
