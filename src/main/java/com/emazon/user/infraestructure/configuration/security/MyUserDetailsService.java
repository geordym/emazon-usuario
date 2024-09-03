package com.emazon.user.infraestructure.configuration.security;


import com.emazon.user.application.dto.infraestructure.InternalUserInfoResponseDto;
import com.emazon.user.application.dto.rest.dto.response.user.UserInfoResponseDto;
import com.emazon.user.application.services.IUserService;
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

    private final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<InternalUserInfoResponseDto> user = userService.getUserInfoByEmail(username);

        if (user.isPresent()) {
            InternalUserInfoResponseDto userObj = user.get();
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .authorities(userObj.getRoles().get(0))
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
