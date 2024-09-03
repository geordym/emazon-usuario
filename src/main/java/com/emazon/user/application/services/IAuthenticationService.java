package com.emazon.user.application.services;

import com.emazon.user.application.dto.infraestructure.InternalUserInfoResponseDto;
import com.emazon.user.application.dto.rest.dto.request.authentication.AuthenticationRequestDto;
import com.emazon.user.domain.model.AuthToken;
import com.emazon.user.domain.ports.in.AuthenticationUseCases;

import java.util.Optional;

public interface IAuthenticationService  {

        AuthToken authenticateUser(AuthenticationRequestDto authenticationRequestDto);
}
