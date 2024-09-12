package com.emazon.user.application.handlers;

import com.emazon.user.application.dto.general.GenericResponseDto;
import com.emazon.user.application.dto.rest.dto.request.user.CreateUserRequestDto;
import com.emazon.user.application.dto.rest.dto.response.user.UserInfoResponseDto;
import com.emazon.user.domain.model.User;

import java.util.List;

public interface IUserRestHandler {
    UserInfoResponseDto getClientInfoById(Long clientId);
    GenericResponseDto createUserWarehouseAssistant(CreateUserRequestDto createUserRequestDto);
    GenericResponseDto createClient(CreateUserRequestDto createUserRequestDto);
    GenericResponseDto createAdministrator(CreateUserRequestDto createUserRequestDto);
    List<User> getUsersByRoleId(Long roleId);

}
