package com.emazon.user.application.services.implementations;
import com.emazon.user.application.services.IUserService;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.in.UserUseCases;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserUseCases userUseCases;

    @Override
    public User createClient(User user) {
        return userUseCases.createClient(user);
    }

    @Override
    public User createWarehouseAssistant(User user) {
        return userUseCases.createWarehouseAssistant(user);
    }

    @Override
    public User createAdministrator(User user) {
        return userUseCases.createAdministrator(user);
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return userUseCases.getUsersByRoleId(roleId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userUseCases.getUserByEmail(email);
    }

    @Override
    public User getClientById(Long clientId) {
        return userUseCases.getClientById(clientId);
    }

}
