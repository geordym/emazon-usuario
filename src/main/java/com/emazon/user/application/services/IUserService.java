package com.emazon.user.application.services;

import com.emazon.user.application.dto.infraestructure.InternalUserInfoResponseDto;
import com.emazon.user.domain.model.User;
import com.emazon.user.application.dto.rest.dto.request.user.CreateUserRequestDto;
import com.emazon.user.application.dto.rest.dto.response.user.UserInfoResponseDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserInfoResponseDto getClientInfoById(Long userId);
    UserInfoResponseDto getUserInfoByAuthenticationContext();
    String createUser(CreateUserRequestDto createUserRequestDto);
    Optional<InternalUserInfoResponseDto> getUserInfoByEmail(String email);
    List<User> getUsersByRoleId(Long roleId);
}
