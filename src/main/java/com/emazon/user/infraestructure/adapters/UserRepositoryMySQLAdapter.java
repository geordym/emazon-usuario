package com.emazon.user.infraestructure.adapters;

import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import com.emazon.user.infraestructure.mapper.UserMapper;
import com.emazon.user.infraestructure.repositories.UserCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRepositoryMySQLAdapter implements UserRepositoryPort {

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


}
