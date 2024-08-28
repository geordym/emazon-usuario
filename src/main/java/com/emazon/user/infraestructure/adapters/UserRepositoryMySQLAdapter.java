package com.emazon.usuario.infraestructure.adapters;

import com.emazon.stock.domain.model.User;
import com.emazon.stock.domain.puertos.out.UserRepositoryPort;
import com.emazon.stock.infraestructure.mapper.UserMapper;
import com.emazon.stock.infraestructure.repositories.UserCrudRepositoryMySQL;
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
    public boolean existsUserByUsername(String username) {
        return userCrudRepositoryMySQL.existsByUsername(username);
    }


}
