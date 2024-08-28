package com.emazon.user.infraestructure.rest.dto.request.authentication;


public class AuthenticationRequestDto {

    private String username;
    private String password;

    // Constructor vacío necesario para la deserialización
    public AuthenticationRequestDto() {
    }

    public AuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
