package com.emazon.user.infraestructure.adapters;

import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.infraestructure.mapper.RoleMapper;
import com.emazon.user.infraestructure.repositories.RoleCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleRepositoryMySQLAdapter implements RoleRepositoryPort {


    private final RoleCrudRepositoryMySQL roleCrudRepositoryMySQL;
    @Override
    public boolean existsRolById(Long id) {
        return roleCrudRepositoryMySQL.existsById(id);
    }

    @Override
    public Role saveRole(Role role) {
        return RoleMapper.entityToDomain(roleCrudRepositoryMySQL.save(RoleMapper.domainToEntity(role)));
    }


}
