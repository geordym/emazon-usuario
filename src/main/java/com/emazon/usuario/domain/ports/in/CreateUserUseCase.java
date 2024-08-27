package com.emazon.usuario.domain.ports.in;

import com.emazon.usuario.domain.model.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
