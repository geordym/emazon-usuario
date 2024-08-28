package com.emazon.user.domain.ports.out;

import com.emazon.user.domain.model.Role;

public interface RoleRepositoryPort {

    boolean existsRolById(Long id);
    Role saveRole(Role role);

}
