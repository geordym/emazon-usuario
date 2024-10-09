package com.emazon.user.application.services;

import com.emazon.user.application.dto.rest.dto.request.authentication.AuthenticationRequestDto;
import com.emazon.user.domain.model.AuthToken;
import com.emazon.user.domain.model.UserAuthentication;

public interface IAuthenticationService  {

        AuthToken authenticateUser(UserAuthentication userAuthentication);
}
