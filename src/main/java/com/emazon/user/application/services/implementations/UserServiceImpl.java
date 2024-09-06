package com.emazon.user.application.services.implementations;


import com.emazon.user.application.dto.security.InternalUserInfoResponseDto;
import com.emazon.user.application.mapper.rest.UserMapper;
import com.emazon.user.application.services.IUserService;
import com.emazon.user.domain.exception.User.ClientNotFoundException;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.in.UserUseCases;
import com.emazon.user.application.dto.rest.dto.request.user.CreateUserRequestDto;
import com.emazon.user.application.dto.rest.dto.response.user.UserInfoResponseDto;
import com.emazon.user.infraestructure.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserUseCases userUseCases;

    @Override
    public UserInfoResponseDto getUserInfoByAuthenticationContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new RuntimeException("Not authenticated user");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            Optional<User> userOptional = userUseCases.getUserByEmail(userDetails.getUsername());

            if(userOptional.isEmpty()){
                throw new RuntimeException("User does not exist by this email");
            }

            User user = userOptional.get();
            UserInfoResponseDto userInfo = new UserInfoResponseDto(user.getId(), user.getEmail(), roles);
        return userInfo;
    }

    @Override
    public void createUser(CreateUserRequestDto createUserRequestDto, RoleEnum roleEnum) {
        User user = UserMapper.dtoToDomain(createUserRequestDto);
        user.setRole(roleEnum.toModel());
        userUseCases.createUser(user);
    }

    @Override
    public Optional<InternalUserInfoResponseDto> getUserInfoByEmail(String email) {
        Optional<User> userOptional = userUseCases.getUserByEmail(email);
        if(userOptional.isEmpty()){
            return Optional.empty();
        }
        User user = userOptional.get();
        InternalUserInfoResponseDto internalUserInfoResponseDto =
                new InternalUserInfoResponseDto(
                        user.getId(), user.getEmail(),
                        Collections.singletonList(user.getRole().getName()), user.getPassword());
        return Optional.of(internalUserInfoResponseDto);
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return userUseCases.getUsersByRoleId(roleId);
    }

    @Override
    public UserInfoResponseDto getClientInfoById(Long clientId) {
        Optional<User> clientOptional= userUseCases.getClientById(clientId);
        if(clientOptional.isEmpty()){
            throw new ClientNotFoundException();
        }

        User user = clientOptional.get();
        UserInfoResponseDto userInfo = new UserInfoResponseDto(
                user.getId(),
                user.getEmail(),
                Collections.singletonList(user.getRole().getName())
        );
        return userInfo;
    }


}
