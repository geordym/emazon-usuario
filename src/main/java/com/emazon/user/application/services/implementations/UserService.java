package com.emazon.user.application.services.implementations;


import com.emazon.user.application.services.IUserService;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.in.UserUseCases;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserUseCases userUseCases;
    @Override
    public User createUser(User user) {
        return userUseCases.createUser(user);
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return userUseCases.getUsersByRoleId(roleId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userUseCases.getUserByEmail(email);
    }

    @Override
    public Optional<User> getClientById(Long clientId) {
        return userUseCases.getClientById(clientId);
    }

}
