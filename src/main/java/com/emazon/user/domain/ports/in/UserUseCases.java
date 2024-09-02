package com.emazon.user.domain.ports.in;

import com.emazon.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserUseCases {
    User createUser(User user);
    List<User> getUsersByRoleId(Long roleId);

    Optional<User> getUserByEmail(String email);
    Optional<User> getClientById(Long clientId);
}
