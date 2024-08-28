package com.emazon.usuario.application.usecases.UserImpl;


import com.emazon.usuario.application.validators.UserValidator;
import com.emazon.usuario.domain.model.User;
import com.emazon.usuario.domain.ports.in.CreateUserUseCase;
import com.emazon.usuario.domain.ports.out.Security.PasswordEncoderPort;
import com.emazon.usuario.domain.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final UserValidator userValidator;

    @Override
    public User createUser(User user) {
        userValidator.validate(user);

        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        return userRepositoryPort.createUser(user);
    }


}
