package com.emazon.user.infraestructure.configuration.security;


import com.emazon.user.application.dto.security.ErrorGenericResponseDto;
import com.emazon.user.domain.ports.out.security.TokenProviderPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {


    private final UserDetailsService userDetailsService;
    private final TokenProviderPort tokenProviderPort;
    private final ObjectMapper objectMapper;

    private final String ERROR_HEADER_RESPONSE = "Authentication Error";
    private final String TOKEN_EXPIRED_MESSAGE = "Token expired";
    private final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials";


    private final ErrorGenericResponseDto tokenExpiredResponse =
            new ErrorGenericResponseDto(ERROR_HEADER_RESPONSE,
                    TOKEN_EXPIRED_MESSAGE, LocalDateTime.now().toString());


    private final ErrorGenericResponseDto invalidCredentialsError =
            new ErrorGenericResponseDto(ERROR_HEADER_RESPONSE,
                    INVALID_CREDENTIALS_MESSAGE, LocalDateTime.now().toString());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String subject = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                subject = tokenProviderPort.extractSubject(jwt);
            } catch (ExpiredJwtException e) {
                String json = objectMapper.writeValueAsString(tokenExpiredResponse);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(json);
                return;
            } catch (JwtException jwtException){
                String json = objectMapper.writeValueAsString(invalidCredentialsError);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(json);
                return;
            }
        }

        if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try{
                CustomUserDetails userDetails = (CustomUserDetails) this.userDetailsService.loadUserByUsername(jwt);
                if (tokenProviderPort.validateToken(jwt, subject)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                            userDetails, jwt, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    createSecurityContext(usernamePasswordAuthenticationToken);
                }
            }catch (UsernameNotFoundException u ){
                String json = objectMapper.writeValueAsString(invalidCredentialsError);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(json);
                return;
            }

        }

        chain.doFilter(request, response);
    }

    private static void createSecurityContext(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }


}
