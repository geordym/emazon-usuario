package com.emazon.user.infraestructure.rest;


import com.emazon.user.application.services.IAuthenticationService;
import com.emazon.user.domain.model.AuthToken;
import com.emazon.user.domain.model.UserAuthentication;
import com.emazon.user.application.dto.rest.dto.request.authentication.AuthenticationRequestDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @Operation(
            summary = SwaggerConstants.AUTHENTICATE_USER_SUMMARY,
            description = SwaggerConstants.AUTHENTICATE_USER_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_CREATED, description = SwaggerConstants.AUTHENTICATE_USER_API_RESPONSES_200_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_NOT_FOUND, description = SwaggerConstants.AUTHENTICATE_USER_API_RESPONSES_404_DESCRIPTION, content = @Content),
    })
    @PostMapping
    public ResponseEntity<AuthToken> authenticateUser(@RequestBody @Valid AuthenticationRequestDto
                                                                  authenticationRequest) throws Exception {
        AuthToken authToken = authenticationService.authenticateUser(authenticationRequest);
        return new ResponseEntity<>(authToken, HttpStatus.OK);
    }


}
