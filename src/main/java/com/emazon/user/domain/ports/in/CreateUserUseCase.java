package com.emazon.user.domain.ports.in;

import com.emazon.user.domain.model.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
