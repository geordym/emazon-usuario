package com.emazon.user.domain.ports.out.Security;

import com.emazon.user.domain.model.UserAuthentication;

import java.time.LocalDateTime;
import java.util.Map;

public interface TokenProviderPort {

    String generateAccessToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt, Map<String, Object> claims);
    String generateRefreshToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt);
}
