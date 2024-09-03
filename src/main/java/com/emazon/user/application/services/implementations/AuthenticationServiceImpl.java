package com.emazon.user.application.services.implementations;

import com.emazon.user.application.dto.rest.dto.request.authentication.AuthenticationRequestDto;
import com.emazon.user.application.services.IAuthenticationService;
import com.emazon.user.domain.model.AuthToken;
import com.emazon.user.domain.model.UserAuthentication;
import com.emazon.user.domain.ports.in.AuthenticationUseCases;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationUseCases authenticationUseCases;

    @Override
    public AuthToken authenticateUser(AuthenticationRequestDto authenticationRequestDto) {
        UserAuthentication userAuthentication =
                new UserAuthentication(authenticationRequestDto.getUsername(),
                        authenticationRequestDto.getPassword());
        return authenticationUseCases.authenticateUser(userAuthentication);
    }


}
