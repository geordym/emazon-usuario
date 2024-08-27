package com.emazon.usuario.application.services;


import com.emazon.usuario.domain.model.User;
import com.emazon.usuario.domain.ports.in.CreateUserUseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final CreateUserUseCase createUserUseCase;
    @Override
    public User createUser(User user) {
        return createUserUseCase.createUser(user);
    }


}
