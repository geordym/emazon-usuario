package com.emazon.user.domain.ports.out;


import com.emazon.user.domain.model.User;

public interface UserRepositoryPort {
    User createUser(User user);

    boolean existsUserByEmail(String email);

    boolean existsUserByIdentityDocument(String identity);
}
