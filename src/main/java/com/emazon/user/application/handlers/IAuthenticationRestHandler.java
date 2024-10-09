package com.emazon.user.application.handlers;

import com.emazon.user.application.dto.rest.dto.request.authentication.AuthenticationRequestDto;
import com.emazon.user.application.dto.rest.dto.response.authentication.AuthenticationResponseDto;
import com.emazon.user.domain.model.AuthToken;

public interface IAuthenticationRestHandler {

    AuthenticationResponseDto authenticateUser(AuthenticationRequestDto authenticationRequestDto);

}
