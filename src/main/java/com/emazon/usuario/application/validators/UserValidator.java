package com.emazon.usuario.application.validators;


import com.emazon.usuario.domain.exception.Role.RoleEmptyException;
import com.emazon.usuario.domain.exception.Role.RoleNotFoundException;
import com.emazon.usuario.domain.exception.User.*;
import com.emazon.usuario.domain.model.Role;
import com.emazon.usuario.domain.model.User;
import com.emazon.usuario.domain.ports.out.RoleRepositoryPort;
import com.emazon.usuario.domain.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.emazon.usuario.domain.util.Constantes.SECURITY_PASSWORD_MIN_LENGTH;


@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;


    public void validate(User user) {
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        validateRole(user.getRole());
    }

    private void validateUsername(String username) {

        if (username == null || username.isEmpty()) {
            throw new UsernameEmptyException("Username cannot be empty");
        }

        if (usernameAlreadyTaken(username)) {
            throw new UsernameAlreadyTakenException("This email is already taken");
        }

        if(!validUsernameEmailFormat(username)){
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

    private boolean validUsernameEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private void validateRole(Role role) {
        if (role == null || role.getName().isEmpty()) {
            throw new RoleEmptyException("Role cannot be empty");
        }
        // Aquí podrías agregar lógica adicional para verificar si el rol existe
        if (!roleExists(role)) {
            throw new RoleNotFoundException("Role does not exist");
        }
    }

    private boolean roleExists(Role role) {
        return roleRepositoryPort.existsRolById(role.getId());
    }

    private boolean usernameAlreadyTaken(String username) {
        return userRepositoryPort.existsUserByUsername(username);
    }
}
