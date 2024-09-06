package com.emazon.user.infraestructure.configuration.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final JwtUtil jwtUtil;
    private final String USERNAME_NOT_FOUND_MESSAGE = "The username has been not found";
    @Override
    public UserDetails loadUserByUsername(String token){
        Long userId = jwtUtil.extractUserId(token);
        String role = jwtUtil.extractRole(token);

        if (userId != null && userId > 0) {
            Collection<GrantedAuthority> authorities = Arrays.asList(
                    new SimpleGrantedAuthority(role)
            );
            CustomUserDetails userDetails = new CustomUserDetails(userId, userId.toString(), token, authorities, true);
            return userDetails;
        } else {
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND_MESSAGE);
        }
    }
}
