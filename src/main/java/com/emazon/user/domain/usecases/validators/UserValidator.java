package com.emazon.user.domain.usecases.validators;


import com.emazon.user.domain.exception.User.*;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.domain.ports.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import static com.emazon.user.domain.util.Constantes.*;


@RequiredArgsConstructor
public class UserValidator {

    public static final String NUMBER_EMPTYORNULL_MESSAGE = "El número de teléfono no puede ser nulo o vacío.";
    public static final String NUMBER_PREFIX = "+";
    public static final String NUMBER_PREFIX_MESSAGE = "Debe contener el codigo de pais al inicio, ejemplo, +57";
    public static final String NUMBER_SIZE_TOOLONG_MESSAGE = "El numero de telefono es demasiado grande";
    public static final String BIRTHDAY_NULL_MESSAGE = "The birthDate cannot be null";
    public static final String PASSWORD_NULLOREMPTY_MESSAGE = "Password cannot be null or empty";
    public static final String ROLE_CANNOT_BE_NULL_OR_EMPTY_MESSAGE = "Role cannot be null or empty";
    private final UserPersistencePort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;


    public void validate(User user) {
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateIdentityDocument(user.getIdentityDocument());
        validateAge(user.getBirthDate());
        validatePhoneNumber(user.getPhoneNumber());
    }


    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException(NUMBER_EMPTYORNULL_MESSAGE);
        }

        if(!phoneNumber.startsWith(NUMBER_PREFIX)){
            throw new IllegalArgumentException(NUMBER_PREFIX_MESSAGE);
        }

        if(phoneNumber.length() > 13){
            throw new IllegalArgumentException(NUMBER_SIZE_TOOLONG_MESSAGE);
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
            throw new IllegalArgumentException(BIRTHDAY_NULL_MESSAGE);
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
            throw new IllegalArgumentException(PASSWORD_NULLOREMPTY_MESSAGE);
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
    private boolean roleExists(Role role) {
        return roleRepositoryPort.existsRolById(role.getId());
    }

    private boolean IsEmailAlreadyTaken(String email) {
        return userRepositoryPort.existsUserByEmail(email);
    }


}
