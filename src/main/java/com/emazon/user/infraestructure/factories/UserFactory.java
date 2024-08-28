package com.emazon.user.infraestructure.factories;


import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.infraestructure.entities.RoleEntity;
import com.emazon.user.infraestructure.entities.UserEntity;

import java.time.LocalDate;

public class UserFactory {

    // Método para crear un UserModel a partir de un DTO

    public static final Long  WAREHOUSEASSISTANT_ROLE_ID = 1L;
    public static final String  WAREHOUSEASSISTANT_ROLE_NAME = "AUX_BODEGA";



  /*  public static User createUser(String username, String password) {
        Role role = new Role(WAREHOUSEASSISTANT_ROLE_ID, WAREHOUSEASSISTANT_ROLE_NAME);
       / return UserFactory.createUser(username,password,role);
    }*/

    public static UserEntity createUserEntity(String firstName, String lastName, String identityDocument,
                                              String phoneNumber, String email, String password,
                                              LocalDate birthDate, RoleEntity role){

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setIdentityDocument(identityDocument);
        userEntity.setPhoneNumber(phoneNumber);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setBirthDate(birthDate);
        userEntity.setRole(role);

        return userEntity;
    }

    public static User createUserModel(String firstName, String lastName, String identityDocument,
                                  String phoneNumber, String email, String password,
                                  LocalDate birthDate, Role role) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setIdentityDocument(identityDocument);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDate(birthDate);
        user.setRole(role);
        return user;
    }

}
