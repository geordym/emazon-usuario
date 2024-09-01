package com.emazon.user.domain.usecases.validators;


import com.emazon.user.domain.exception.Role.RoleNotFoundException;
import com.emazon.user.domain.exception.User.*;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import static com.emazon.user.domain.util.Constantes.*;


@RequiredArgsConstructor
public class UserValidator {

    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;


    public void validate(User user) {
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateRole(user.getRole());
        validateIdentityDocument(user.getIdentityDocument());
        validateAge(user.getBirthDate());
        validatePhoneNumber(user.getPhoneNumber());
    }


    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono no puede ser nulo o vacío.");
        }

        String regex = "^\\+?\\d{1,13}$";
        if (!phoneNumber.matches(regex)) {
            throw new IllegalArgumentException("El número de teléfono no es válido. Debe tener hasta 13 dígitos y puede contener solo un símbolo de '+' al inicio.");
        }

        if (phoneNumber.startsWith("+")) {
            if (phoneNumber.length() > 14) {
                throw new IllegalArgumentException("El número de teléfono no puede tener más de 13 dígitos después del '+'.");
            }
        } else {
            if (phoneNumber.length() > 13) {
                throw new IllegalArgumentException("El número de teléfono no puede tener más de 13 dígitos.");
            }
        }
    }


    private void validateAge(LocalDate birthDate) {
        Integer edad = calculateAge(birthDate);

        if(!IsOfLegalAge(edad)){
            throw  new UnderageUserException();
        }
    }

    private boolean IsOfLegalAge(Integer edad){
        return edad >= MINIMUM_USER_AGE;
    }

    public static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("The birthDate cannot be null");
        }

        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private void validateIdentityDocument(String identityDocument) {

        if(IsIdentityDocumentRegister(identityDocument)){
            throw new IdentityDocumentTakenException();
        }

    }

    private void validateEmail(String email) {

        if (IsEmailAlreadyTaken(email)) {
            throw new EmailAlreadyTakenException();
        }

        if(!IsValidEmailFormat(email)){
            throw new InvalidEmailFormatException();
        }

    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty() ) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if(!validPasswordFormat(password)){
            throw new PasswordFormatException();
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
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        // Aquí podrías agregar lógica adicional para verificar si el rol existe
        if (!roleExists(role)) {
            throw new RoleNotFoundException(role.getId());
        }
    }

    private boolean roleExists(Role role) {
        return roleRepositoryPort.existsRolById(role.getId());
    }

    private boolean IsEmailAlreadyTaken(String email) {
        return userRepositoryPort.existsUserByEmail(email);
    }


}
