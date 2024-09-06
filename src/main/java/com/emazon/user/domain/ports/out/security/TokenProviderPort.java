package com.emazon.user.domain.ports.out.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface TokenProviderPort {

    String generateAccessToken(LocalDateTime issuedAt, Long subject, LocalDateTime expirationAt, Map<String, Object> claims);
    String generateRefreshToken(LocalDateTime issuedAt, Long subject, LocalDateTime expirationAt);
    Object extractClaim(String token, String claimKey);
    String extractUsername(String token);
    Map<String, Object> extractAllClaims(String token);
    Date extractExpiration(String token);
    Boolean validateToken(String token, String username);
    Boolean isTokenExpired(String token);
}
