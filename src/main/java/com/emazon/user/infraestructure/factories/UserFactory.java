package com.emazon.usuario.infraestructure.factories;


import com.emazon.usuario.domain.model.Role;
import com.emazon.usuario.domain.model.User;

public class UserFactory {

    // Método para crear un UserModel a partir de un DTO

    public static final Long  WAREHOUSEASSISTANT_ROLE_ID = 1L;
    public static final String  WAREHOUSEASSISTANT_ROLE_NAME = "AUX_BODEGA";



    public static User createUserWarehouseAssistant(String username, String password) {
        Role role = new Role(WAREHOUSEASSISTANT_ROLE_ID, WAREHOUSEASSISTANT_ROLE_NAME);
        return UserFactory.createUser(username,password,role);
    }

    private static User createUser(String username, String password, Role role) {
        //String encryptedPassword = encryptPassword(dto.getPassword());
        User user = new User();
        user.setUsername(username); // O cualquier otro campo que consideres para username
        user.setPassword(password);
        user.setRole(role);
        return user;
    }


}
