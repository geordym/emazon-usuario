package com.emazon.user.domain.ports.out;


import com.emazon.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User createUser(User user);

    boolean existsUserByEmail(String email);

    boolean existsUserByIdentityDocument(String identity);

    Optional<User> findByEmail(String email);
    Optional<User> findClientById(Long clientId);

    List<User> getUsersByRoleId(Long roleId);

}
