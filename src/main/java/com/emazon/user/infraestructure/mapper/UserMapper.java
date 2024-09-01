package com.emazon.user.infraestructure.mapper;

import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.infraestructure.entities.UserEntity;
import com.emazon.user.infraestructure.factories.UserFactory;
import com.emazon.user.infraestructure.rest.dto.request.User.CreateUserRequestDto;

import java.util.Optional;

public class UserMapper {


    public static UserEntity domainToEntity(User user){
        UserEntity userEntity = UserFactory.createUserEntity(
                user.getFirstName(),
                user.getLastName(),
                user.getIdentityDocument(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthDate(),
                RoleMapper.domainToEntity(user.getRole())
                );

        userEntity.setId(user.getId());
        return userEntity;
    }

    public static User entityToDomain(UserEntity user){
       User userModel = UserFactory.createUserModel(
               user.getId(),
               user.getFirstName(),
               user.getLastName(),
               user.getIdentityDocument(),
               user.getPhoneNumber(),
               user.getEmail(),
               user.getPassword(),
               user.getBirthDate(),
               RoleMapper.entityToDomain(user.getRole())
       );

       userModel.setId(user.getId());
       return userModel;
    }

    public static User dtoToDomain(CreateUserRequestDto createUserRequestDto){
        Role role = new Role(createUserRequestDto.getId_role(), "");
        User userModel =  UserFactory.createUserModel(
                null,
                createUserRequestDto.getFirstName(),
                createUserRequestDto.getLastName(),
                createUserRequestDto.getIdentityDocument(),
                createUserRequestDto.getPhoneNumber(),
                createUserRequestDto.getEmail(),
                createUserRequestDto.getPassword(),
                createUserRequestDto.getBirthDate(),
                role
        );

        return userModel;
    }

    public static Optional<User> optionalEntityToOptionalDomain(Optional<UserEntity> userEntityOpt){
        if(userEntityOpt.isEmpty()){
            return Optional.empty();
        }

        UserEntity userEntity = userEntityOpt.get();
        User userModel =  UserFactory.createUserModel(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getIdentityDocument(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getBirthDate(),
                RoleMapper.entityToDomain(userEntity.getRole())
        );

        return Optional.of(userModel);
    }


}
