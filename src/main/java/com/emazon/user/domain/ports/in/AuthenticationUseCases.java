package com.emazon.user.domain.ports.in;

import com.emazon.user.domain.model.AuthToken;
import com.emazon.user.domain.model.UserAuthentication;

public interface AuthenticationUseCases {

    AuthToken authenticateUser(UserAuthentication userAuthentication);

}
