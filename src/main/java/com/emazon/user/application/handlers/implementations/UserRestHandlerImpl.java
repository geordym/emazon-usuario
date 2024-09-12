package com.emazon.user.application.handlers.implementations;

import com.emazon.user.application.dto.general.GenericResponseDto;
import com.emazon.user.application.dto.rest.dto.request.user.CreateUserRequestDto;
import com.emazon.user.application.dto.rest.dto.response.user.UserInfoResponseDto;
import com.emazon.user.application.handlers.IUserRestHandler;
import com.emazon.user.application.mapper.rest.UserMapper;
import com.emazon.user.application.services.IUserService;
import com.emazon.user.domain.model.User;
import com.emazon.user.infraestructure.rest.constants.RestConstants;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class UserRestHandlerImpl implements IUserRestHandler {

    private final IUserService userService;
    @Override
    public UserInfoResponseDto getClientInfoById(Long clientId) {
        User user = userService.getClientById(clientId);
        return UserMapper.domainToDto(user);
    }

    @Override
    public GenericResponseDto createUserWarehouseAssistant(CreateUserRequestDto createUserRequestDto) {
        User user = userService.createWarehouseAssistant(UserMapper.dtoToDomain(createUserRequestDto));
        GenericResponseDto genericResponseDto = new GenericResponseDto(RestConstants.CREATE_USER_WAREHOUSE_ASSISTANT_MESSAGE, LocalDateTime.now().toString());
        return genericResponseDto;
    }

    @Override
    public GenericResponseDto createClient(CreateUserRequestDto createUserRequestDto) {
        User user = userService.createClient(UserMapper.dtoToDomain(createUserRequestDto));
        GenericResponseDto genericResponseDto = new GenericResponseDto(RestConstants.CREATE_USER_WAREHOUSE_ASSISTANT_MESSAGE, LocalDateTime.now().toString());
        return genericResponseDto;
    }

    @Override
    public GenericResponseDto createAdministrator(CreateUserRequestDto createUserRequestDto) {
        User user = userService.createAdministrator(UserMapper.dtoToDomain(createUserRequestDto));
        GenericResponseDto genericResponseDto = new GenericResponseDto(RestConstants.CREATE_USER_WAREHOUSE_ASSISTANT_MESSAGE, LocalDateTime.now().toString());
        return genericResponseDto;
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return userService.getUsersByRoleId(roleId);
    }


}
