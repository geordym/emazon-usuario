package com.emazon.user.infraestructure.adapters;

import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.out.UserPersistencePort;
import com.emazon.user.infraestructure.entities.UserEntity;
import com.emazon.user.infraestructure.enums.RoleEnum;
import com.emazon.user.application.mapper.rest.UserMapper;
import com.emazon.user.infraestructure.repositories.UserCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserPersistenceMySQLAdapter implements UserPersistencePort {

    private final UserCrudRepositoryMySQL userCrudRepositoryMySQL;

    @Override
    public User createUser(User user) {
        return UserMapper.entityToDomain(userCrudRepositoryMySQL.save(UserMapper.domainToEntity(user)));
    }

    @Override
    public boolean existsUserByEmail(String username) {
        return userCrudRepositoryMySQL.existsByEmail(username);
    }

    @Override
    public boolean existsUserByIdentityDocument(String identity) {
        return userCrudRepositoryMySQL.existsByIdentityDocument(identity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntityOpt = userCrudRepositoryMySQL.findByEmail(email);
        return UserMapper.optionalEntityToOptionalDomain(userEntityOpt);
    }

    @Override
    public Optional<User> findClientById(Long clientId) {
        Optional<UserEntity> clientEntityOpt = userCrudRepositoryMySQL.findClientById(RoleEnum.CLIENTE.getId(), clientId);
        if(clientEntityOpt.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(UserMapper.entityToDomain(clientEntityOpt.get()));
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return userCrudRepositoryMySQL.findAllByIdRole(roleId).stream().map(UserMapper::entityToDomain).collect(Collectors.toList());
    }


}
