package com.emazon.usuario.domain.ports.out.Security;

public interface PasswordEncoderPort {

    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);

}
