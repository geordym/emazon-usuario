package com.emazon.user.application.services;

import com.emazon.user.domain.model.User;
import com.emazon.user.application.dto.rest.dto.request.user.CreateUserRequestDto;
import com.emazon.user.application.dto.rest.dto.response.user.UserInfoResponseDto;

import java.util.List;

public interface IUserService {
    UserInfoResponseDto getClientInfoById(Long userId);
    UserInfoResponseDto getUserInfoByAuthenticationContext();
    String createUser(CreateUserRequestDto createUserRequestDto);

    List<User> getUsersByRoleId(Long roleId);
}
