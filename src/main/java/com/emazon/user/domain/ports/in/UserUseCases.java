package com.emazon.user.domain.ports.in;

import com.emazon.user.domain.model.User;

import java.util.List;

public interface UserUseCases {
    User createClient(User user);
    User createWarehouseAssistant(User user);
    User createAdministrator(User user);
    List<User> getUsersByRoleId(Long roleId);

    User getUserByEmail(String email);
    User getClientById(Long clientId);
}
