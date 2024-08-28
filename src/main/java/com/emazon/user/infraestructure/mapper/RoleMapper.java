package com.emazon.user.infraestructure.mapper;

import com.emazon.user.domain.model.Role;
import com.emazon.user.infraestructure.entities.RoleEntity;

public class RoleMapper {


    public static RoleEntity domainToEntity(Role role){
        return new RoleEntity(role.getId(), role.getName());
    }

    public static Role entityToDomain(RoleEntity role){
        return new Role(role.getId(), role.getName());
    }

}
