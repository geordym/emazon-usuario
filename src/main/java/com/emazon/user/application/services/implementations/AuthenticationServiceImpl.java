package com.emazon.user.application.services.implementations;

import com.emazon.user.application.services.IAuthenticationService;
import com.emazon.user.domain.model.AuthToken;
import com.emazon.user.domain.model.UserAuthentication;
import com.emazon.user.domain.ports.in.AuthenticationUseCases;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationUseCases authenticationUseCases;

    @Override
    public AuthToken authenticateUser(UserAuthentication userAuthentication) {
        return authenticationUseCases.authenticateUser(userAuthentication);
    }

}
