package com.emazon.user.domain.usecases;

import com.emazon.user.domain.exception.User.UserNotFoundException;
import com.emazon.user.domain.usecases.validators.UserValidator;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.in.UserUseCases;
import com.emazon.user.domain.ports.out.security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.UserPersistencePort;
import com.emazon.user.domain.enums.RoleEnum;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserUseCasesImpl implements UserUseCases {


    private final PasswordEncoderPort passwordEncoderPort;
    private final UserValidator userValidator;
    private final UserPersistencePort userRepositoryPort;

    @Override
    public User createClient(User user) {
        userValidator.validate(user);
        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        user.setRole(RoleEnum.CLIENTE.toModel());
        return userRepositoryPort.createUser(user);
    }

    @Override
    public User createWarehouseAssistant(User user) {
        userValidator.validate(user);
        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        user.setRole(RoleEnum.WAREHOUSE_ASSISTANT.toModel());
        return userRepositoryPort.createUser(user);
    }

    @Override
    public User createAdministrator(User user) {
        userValidator.validate(user);
        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        user.setRole(RoleEnum.ADMINISTRADOR.toModel());
        return userRepositoryPort.createUser(user);
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return userRepositoryPort.getUsersByRoleId(roleId);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepositoryPort.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException();
        }

        return userOptional.get();
    }

    @Override
    public User getClientById(Long clientId) {
        Optional<User> userOptional = userRepositoryPort.findClientById(clientId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException();
        }

        return userOptional.get();
    }


}
