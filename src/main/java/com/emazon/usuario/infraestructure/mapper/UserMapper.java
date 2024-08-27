package com.emazon.usuario.infraestructure.mapper;

import com.emazon.usuario.domain.model.User;
import com.emazon.usuario.infraestructure.entities.UserEntity;

public class UserMapper {


    public static UserEntity domainToEntity(User user){
        return new UserEntity(user.getId(), user.getUsername(), user.getPassword(), RoleMapper.domainToEntity(user.getRole()));
    }

    public static User entityToDomain(UserEntity user){
        return new User(user.getId(),user.getUsername(), user.getPassword(), RoleMapper.entityToDomain(user.getRole()));
    }


}
