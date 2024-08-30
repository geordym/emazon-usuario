package com.emazon.user.application.services.implementations;


import com.emazon.user.application.services.IUserService;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.in.UserUseCases;
import lombok.RequiredArgsConstructor;

import java.util.List;


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

}
