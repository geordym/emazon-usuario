package com.emazon.user.infraestructure.configuration.security;


import com.emazon.user.domain.exception.User.UsernameNotFoundException;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepositoryPort.findByEmail(username);

        if (user.isPresent()) {
            User userObj = user.get();
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getEmail())
                    .password(userObj.getPassword())
                    .authorities(userObj.getRole().getName())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();

            return userDetails;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }


}
