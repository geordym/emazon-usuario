package com.emazon.user.infraestructure.configuration.security;


import com.emazon.user.application.dto.infraestructure.InternalUserInfoResponseDto;
import com.emazon.user.application.services.IUserService;
import com.emazon.user.domain.ports.out.security.TokenProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {


    private final UserDetailsService userDetailsService;
    private final TokenProviderPort tokenProviderPort;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = tokenProviderPort.extractUsername(jwt);
            } catch (ExpiredJwtException e) {
                String isRefreshToken = request.getHeader("isRefreshToken");
                String requestURL = request.getRequestURL().toString();
                if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
                    allowForRefreshToken(e, request, response);
                }
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (tokenProviderPort.validateToken(jwt, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }


        chain.doFilter(request, response);
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> claims = ex.getClaims();
        String username = (String) claims.get(Claims.SUBJECT);
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);

        if (tokenProviderPort.isTokenExpired(jwt)) {
            throw new RuntimeException("Refresh token has expired");
        }

        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expirationAt = issuedAt.plusMinutes(15);
        String newAccessToken = tokenProviderPort.generateAccessToken(issuedAt, username, expirationAt, claims);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        response.setHeader("Authorization", "Bearer " + newAccessToken);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.setAttribute("claims", claims);
    }
}
