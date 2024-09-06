package com.emazon.user.domain.usecases;

import com.emazon.user.application.dto.rest.dto.request.authentication.AuthenticationRequestDto;
import com.emazon.user.domain.exception.User.InvalidUsernameOrPasswordException;
import com.emazon.user.domain.model.AuthToken;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.model.UserAuthentication;
import com.emazon.user.domain.ports.in.AuthenticationUseCases;
import com.emazon.user.domain.ports.out.security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.security.TokenProviderPort;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

import static com.emazon.user.domain.configuration.JwtSecurityConstants.*;


@RequiredArgsConstructor
public class AuthenticationUseCasesImpl implements AuthenticationUseCases {

    private final PasswordEncoderPort passwordEncoderPort;
    private final UserRepositoryPort userRepositoryPort;
    private final TokenProviderPort tokenProviderPort;



    @Override
    public AuthToken authenticateUser(UserAuthentication userAuthentication) {
        String username = userAuthentication.getEmail();
        Optional<User> user = userRepositoryPort.findByEmail(username);

        if(user.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }

        User userFounded = user.get();
        validatePassword(userAuthentication.getPassword(), userFounded.getPassword());
        Map<String, Object> claims = createClaims(userFounded);

        String accessToken = generateAccessToken(String.valueOf(userFounded.getId()), claims);
        String refreshToken = generateRefreshToken(String.valueOf(userFounded.getId()));

        AuthToken authToken = new AuthToken(accessToken,refreshToken);
        return authToken;
    }

    public Map<String, Object> createClaims(User user){
        Map<String, Object> claims = new HashMap<>();
       claims.put(CLAIM_SUBJECT_KEY, user.getEmail());
        claims.put(KEY_ROLE_CLAIM, user.getRole().getName());
        return claims;
    }



    private void validatePassword(String providedPassword, String storedPassword) {
        boolean isPasswordValid = passwordEncoderPort.matches(providedPassword, storedPassword);
        if (!isPasswordValid) {
            throw new InvalidUsernameOrPasswordException();
        }
    }

    public String generateAccessToken(String subject, Map<String, Object> claims) {
        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expirationAt = issuedAt.plusMinutes(ACCESS_TOKEN_DURATION_MINUTES);
        return tokenProviderPort.generateAccessToken(issuedAt, subject, expirationAt, claims);
    }

    public String generateRefreshToken(String subject) {
        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expirationAt = issuedAt.plusMinutes(REFRESH_TOKEN_DURATION_MINUTES);
        return tokenProviderPort.generateRefreshToken(issuedAt, subject, expirationAt);
    }


}
