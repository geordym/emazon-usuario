package com.emazon.usuario.domain.ports.out;


import com.emazon.usuario.domain.model.User;

public interface UserRepositoryPort {
    User createUser(User user);

    boolean existsUserByUsername(String username);
}
