package com.emazon.user.application.services;


import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.in.CreateUserUseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final CreateUserUseCase createUserUseCase;
    @Override
    public User createUser(User user) {
        return createUserUseCase.createUser(user);
    }


}
