package com.emazon.user.application.validators;


import com.emazon.user.domain.exception.Role.RoleEmptyException;
import com.emazon.user.domain.exception.Role.RoleNotFoundException;
import com.emazon.user.domain.exception.User.*;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.emazon.user.domain.util.Constantes.SECURITY_PASSWORD_MIN_LENGTH;
import static com.emazon.user.domain.util.Constantes.USER_EMAIL_REGEX_VALIDATION;


@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;


    public void validate(User user) {
        validateUsername(user.getEmail());
        validatePassword(user.getPassword());
        validateRole(user.getRole());
        validateIdentityDocument(user.getIdentityDocument());
    }

    private void validateIdentityDocument(String identityDocument) {

        if(IsIdentityDocumentRegister(identityDocument)){
            throw new InvalidIdentityDocumentException("Identity document is invalid or missing.");
        }

    }

    private void validateUsername(String username) {

        if (username == null || username.isEmpty()) {
            throw new UsernameEmptyException("Username cannot be empty");
        }

        if (IsEmailAlreadyTaken(username)) {
            throw new UsernameAlreadyTakenException("This email is already taken");
        }

        if(!IsValidEmailFormat(username)){
            throw new InvalidEmailFormatException("Username must be a email");
        }

    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty() ) {
            throw new PasswordEmptyException("Password cannot be empty");
        }

        if(!validPasswordFormat(password)){
            throw new PasswordFormatException("Password is not valid");
        }
    }

    private boolean validPasswordFormat(String password){
        if(password.length() < SECURITY_PASSWORD_MIN_LENGTH) {
            return false;
        }

        return true;
    }

    private boolean IsIdentityDocumentRegister(String document){
        return userRepositoryPort.existsUserByIdentityDocument(document);
    }

    private boolean IsValidEmailFormat(String email) {
        String emailRegex = USER_EMAIL_REGEX_VALIDATION;
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private void validateRole(Role role) {
        if (role == null) {
            throw new RoleEmptyException("Role cannot be null");
        }
        // Aquí podrías agregar lógica adicional para verificar si el rol existe
        if (!roleExists(role)) {
            throw new RoleNotFoundException("Role does not exist");
        }
    }

    private boolean roleExists(Role role) {
        return roleRepositoryPort.existsRolById(role.getId());
    }

    private boolean IsEmailAlreadyTaken(String username) {
        return userRepositoryPort.existsUserByEmail(username);
    }
}
