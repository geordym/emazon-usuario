package com.emazon.user.infraestructure.adapters.security;

import com.emazon.user.domain.ports.out.security.TokenProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class JwtIOTokenAdapter implements TokenProviderPort {


    private String SECRET_KEY = "3nKAe0ytwn6XSOf/7mI7mmyiRrdVvcl4YVy9kG6ChaI=";
    private final String CLAIM_SUBJECT_KEY = "sub";
    private final String CLAIM_EXPIRATION_KEY = "exp";
    private final String CLAIM_EXPEDITION_KEY = "iat";


    @Override
    public String generateAccessToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt, Map<String, Object> claims) {
        return createAccessToken(claims, subject, issuedAt, expirationAt);
    }

    @Override
    public String generateRefreshToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt) {
        return createRefreshToken(subject, issuedAt, expirationAt);
    }

    @Override
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = (String) extractClaim(token, CLAIM_SUBJECT_KEY);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }


    @Override
    public Boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate.before(new Date());
    }

    @Override
    public Object extractClaim(String token, String claimKey) {
        Map<String, Object> claims = extractAllClaims(token);
        return claims.get(claimKey);
    }

    @Override
    public String extractUsername(String token) {
        return (String) extractClaim(token, CLAIM_SUBJECT_KEY);
    }

    @Override
    public Map<String, Object> extractAllClaims(String token) {
        Claims claims =  Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build().parseSignedClaims(token).getPayload();
        return claims;
    }

    @Override
    public Date extractExpiration(String token) {
        return (Date) extractClaim(token, CLAIM_EXPIRATION_KEY);
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
