package com.emazon.user.application.dto.security;

import java.util.List;

public class InternalUserInfoResponseDto {

    public Long idUser;
    public String username;
    private List<String> roles;
    private String password;

    public InternalUserInfoResponseDto(Long idUser, String username, List<String> roles, String password) {
        this.idUser = idUser;
        this.username = username;
        this.roles = roles;
        this.password = password;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
