package com.emazon.usuario.infraestructure.mapper;

import com.emazon.usuario.domain.model.Role;
import com.emazon.usuario.infraestructure.entities.RoleEntity;

public class RoleMapper {


    public static RoleEntity domainToEntity(Role role){
        return new RoleEntity(role.getId(), role.getName());
    }

    public static Role entityToDomain(RoleEntity role){
        return new Role(role.getId(), role.getName());
    }

}
