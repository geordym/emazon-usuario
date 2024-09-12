package com.emazon.user.domain.usecases;


import com.emazon.user.domain.exception.User.InvalidUsernameOrPasswordException;
import com.emazon.user.domain.model.AuthToken;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.model.UserAuthentication;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import com.emazon.user.domain.ports.out.security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.security.TokenProviderPort;
import com.emazon.user.infraestructure.adapters.security.EncryptionBCryptAdapter;
import com.emazon.user.domain.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.emazon.user.domain.configuration.JwtSecurityConstants.*;
import static com.emazon.user.domain.util.Constantes.SECURITY_EMAIL_MIN_LENGTH;
import static com.emazon.user.domain.util.Constantes.SECURITY_PASSWORD_MIN_LENGTH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthenticationUseCaseImplTest {


    @Mock
    private UserRepositoryPort userRepositoryPort;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;
    @Mock
    private TokenProviderPort tokenProviderPort;

    @InjectMocks
    private AuthenticationUseCasesImpl authUseCases;

    @Mock
    private  EncryptionBCryptAdapter cryptAdapter = new EncryptionBCryptAdapter(new BCryptPasswordEncoder());

    private AuthToken authToken =
            new AuthToken("asfsafaskgagklsajgksagjksaf", "asfjsajfsajfjsaf");

    private Role roleClient = new Role(RoleEnum.CLIENTE.getId(), RoleEnum.CLIENTE.getName());
    private User user = new User(1L, "a", "admin1234", roleClient);
    UserAuthentication userAuthentication = new UserAuthentication("asd@gmail.com", "admin1234");

    @BeforeEach
    void setup(){
        //authUseCases = new AuthenticationUseCasesImpl(cryptAdapter, userRepositoryPort,tokenProviderPort);
    }



    @Test
    public void authenticateUser_WhenCalledWithValidUserAuthentication_ReturnAuthToken(){

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_SUBJECT_KEY, user.getId());
        claims.put(CLAIM_KEY_ROLE, user.getRole().getName());

        when(userRepositoryPort.findByEmail(userAuthentication.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoderPort.matches(userAuthentication.getPassword(), user.getPassword())).thenReturn(true);
        when(authUseCases.generateAccessToken(String.valueOf(user.getId()),claims)).thenReturn(authToken.getAccessToken());
        when(authUseCases.generateRefreshToken(null)).thenReturn(authToken.getAccessToken());
        AuthToken authTokenReturned = authUseCases.authenticateUser(userAuthentication);

        assertEquals("Equals tokens", authTokenReturned.getAccessToken(), authToken.getAccessToken());
    }

    @Test
    public void authenticateUser_WhenCalledWithNullUserAuthentication_ReturnsException(){
        UserAuthentication userAuthentication = new UserAuthentication(null,null);

        assertThrows(IllegalArgumentException.class, () -> {
            authUseCases.authenticateUser(userAuthentication);
        });
    }

    @Test
    public void authenticateUser_WhenCalledWithValidLengthUserAuthentication_DoesNotReturnsException(){
        UserAuthentication userAuthentication = new UserAuthentication("a".repeat(SECURITY_EMAIL_MIN_LENGTH + 1),"a".repeat(SECURITY_PASSWORD_MIN_LENGTH + 1));
        assertDoesNotThrow(() -> {
            authUseCases.authenticateUser(userAuthentication);
        });
    }

    @Test
    public void authenticateUser_WhenCalledWithInvalidLengthUserAuthentication_ReturnsException(){
        UserAuthentication userAuthentication = new UserAuthentication("a".repeat(SECURITY_EMAIL_MIN_LENGTH-1),"a".repeat(SECURITY_PASSWORD_MIN_LENGTH-1));

        assertThrows(IllegalArgumentException.class, () -> {
            authUseCases.authenticateUser(userAuthentication);
        });
    }
    @Test
    public void authenticateUser_WhenCalledWithUserDoesNotExist_ReturnsException(){
        UserAuthentication userAuthentication = new UserAuthentication("a".repeat(SECURITY_EMAIL_MIN_LENGTH),"a".repeat(SECURITY_PASSWORD_MIN_LENGTH));

        when(userRepositoryPort.findByEmail(userAuthentication.getEmail())).thenReturn(Optional.empty());

        assertThrows(InvalidUsernameOrPasswordException.class, () -> {
            authUseCases.authenticateUser(userAuthentication);
        });
    }



    @Test
    public void authenticateUser_WhenCalledWithInvalidUserAuthentication_ReturnsException(){

        when(userRepositoryPort.findByEmail(userAuthentication.getEmail())).thenReturn(Optional.empty());
        when(authUseCases.authenticateUser(userAuthentication)).thenReturn(authToken);


        assertThrows(InvalidUsernameOrPasswordException.class, () -> {
            authUseCases.authenticateUser(userAuthentication);
        });

    }

    @Test
    public void createClaims_WhenCalledWithValidUser_ThenReturnMap(){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_SUBJECT_KEY, user.getId());
        claims.put(CLAIM_KEY_ROLE, user.getRole().getName());

        Map<String, Object> claimsReturned = authUseCases.createClaims(user);

        assertEquals("The claims are equals", claims, claimsReturned);
    }

    @Test
    public void createClaims_WhenCalledWithNullUser_ThenReturnException(){
        user = null;

        assertThrows(IllegalArgumentException.class, () -> {
           authUseCases.createClaims(user);
        });
    }

    @Test
    public void createClaims_WhenCalledWithNullId_ThenReturnException(){
        user.setId(null);

        assertThrows(IllegalArgumentException.class, () -> {
            authUseCases.createClaims(user);
        });
    }

    @Test
    public void createClaims_WhenCalledWithNullRole_ThenReturnException(){
        user.setRole(null);

        assertThrows(IllegalArgumentException.class, () -> {
            authUseCases.createClaims(user);
        });
    }

    @Test
    public void createClaims_WhenCalledWithNullRoleName_ThenReturnException(){
        user.setRole(new Role(0L, null));

        assertThrows(IllegalArgumentException.class, () -> {
            authUseCases.createClaims(user);
        });
    }

    @Test
    public void validatePassword_WhenCalledWithNullParameters_ThenReturnException(){
        String providedPassword = "";
        String storedPassword = "";

        assertThrows(IllegalArgumentException.class, () -> {
            authUseCases.validatePassword(providedPassword, storedPassword);
        });
    }



}
