package com.emazon.user.infraestructure.adapters.security;

import com.emazon.user.domain.ports.out.Security.TokenProviderPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class JwtIOTokenAdapter implements TokenProviderPort {


    private String SECRET_KEY = "3nKAe0ytwn6XSOf/7mI7mmyiRrdVvcl4YVy9kG6ChaI=";

    @Override
    public String generateAccessToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt, Map<String, Object> claims) {
        return createAccessToken(claims, subject, issuedAt, expirationAt);
    }

    @Override
    public String generateRefreshToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt) {
        return createRefreshToken(subject, issuedAt, expirationAt);
    }

    private String createAccessToken(Map<String, Object> claims, String subject, LocalDateTime issuedAt, LocalDateTime expirationAt) {
        Date issuedAtDate = convertToDate(issuedAt);
        Date expirationDate = convertToDate(expirationAt);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(issuedAtDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    private String createRefreshToken(String subject, LocalDateTime issuedAt, LocalDateTime expirationAt) {
        Date issuedAtDate = issuedAt != null ? convertToDate(issuedAt) : new Date();
        Date expirationDate = expirationAt != null ? convertToDate(expirationAt) : new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30); // 30 días por defecto

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAtDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    private Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
