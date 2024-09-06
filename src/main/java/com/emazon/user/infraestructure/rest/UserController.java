package com.emazon.user.infraestructure.rest;


import com.emazon.user.application.dto.general.GenericResponseDto;
import com.emazon.user.application.dto.rest.dto.request.user.CreateUserRequestDto;
import com.emazon.user.application.services.IUserService;
import com.emazon.user.application.dto.rest.dto.response.user.UserInfoResponseDto;
import com.emazon.user.infraestructure.enums.RoleEnum;
import com.emazon.user.infraestructure.rest.constants.HttpStatusCodes;
import com.emazon.user.infraestructure.rest.constants.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.emazon.user.infraestructure.rest.constants.RestConstants.CREATE_USER_CLIENT_MESSAGE;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/info/client/{clientId}")
    public ResponseEntity<UserInfoResponseDto> getClientInfo(@PathVariable("clientId") Long clientId) {
        UserInfoResponseDto userInfo = userService.getClientInfoById(clientId);
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoResponseDto> getUserInfoByAuthenticationContext() {
        UserInfoResponseDto userInfoResponseDto = userService.getUserInfoByAuthenticationContext();
        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoResponseDto);
    }


    @Operation(
            summary = SwaggerConstants.REGISTER_CLIENT_SUMMARY,
            description = SwaggerConstants.REGISTER_CLIENT_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_CREATED, description = SwaggerConstants.REGISTER_CLIENT_API_RESPONSES_201_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_BAD_REQUEST, description = SwaggerConstants.REGISTER_CLIENT_API_RESPONSES_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_CONFLICT, description = SwaggerConstants.REGISTER_CLIENT_API_RESPONSES_409_DESCRIPTION, content = @Content)
    })
    @PostMapping("/register/client")
    public ResponseEntity<GenericResponseDto> registerUserClient(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        userService.createUser(createUserRequestDto, RoleEnum.CLIENTE);
        GenericResponseDto genericResponseDto = new GenericResponseDto(CREATE_USER_CLIENT_MESSAGE, LocalDateTime.now().toString());
        return new ResponseEntity<>(genericResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/register/warehouse-assistant")
    @Operation(
            summary = SwaggerConstants.REGISTER_WAREHOUSE_ASSISTANT_SUMMARY,
            description = SwaggerConstants.REGISTER_WAREHOUSE_ASSISTANT_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_CREATED, description = SwaggerConstants.REGISTER_WAREHOUSE_ASSISTANT_API_RESPONSES_201_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_BAD_REQUEST, description = SwaggerConstants.REGISTER_WAREHOUSE_ASSISTANT_API_RESPONSES_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_CONFLICT, description = SwaggerConstants.REGISTER_WAREHOUSE_ASSISTANT_API_RESPONSES_409_DESCRIPTION, content = @Content)
    })
    public ResponseEntity<GenericResponseDto> registerUserWarehouseAssistant(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        userService.createUser(createUserRequestDto, RoleEnum.WAREHOUSE_ASSISTANT);
        GenericResponseDto genericResponseDto = new GenericResponseDto(SwaggerConstants.CREATE_USER_WAREHOUSE_ASSISTANT_MESSAGE, LocalDateTime.now().toString());
        return new ResponseEntity<>(genericResponseDto, HttpStatus.CREATED);
    }



}
