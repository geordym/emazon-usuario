package com.emazon.user.domain.usecases;

import com.emazon.user.application.validators.UserValidator;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.in.UserUseCases;
import com.emazon.user.domain.ports.out.Security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserUseCasesImpl implements UserUseCases {


    private final PasswordEncoderPort passwordEncoderPort;
    private final UserValidator userValidator;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User createUser(User user) {
        userValidator.validate(user);
        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        return userRepositoryPort.createUser(user);
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return userRepositoryPort.getUsersByRoleId(roleId);
    }


}
