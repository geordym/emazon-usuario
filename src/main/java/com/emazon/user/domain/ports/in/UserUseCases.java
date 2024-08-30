package com.emazon.user.domain.ports.in;

import com.emazon.user.domain.model.User;

import java.util.List;

public interface UserUseCases {
    User createUser(User user);
    List<User> getUsersByRoleId(Long roleId);
}
