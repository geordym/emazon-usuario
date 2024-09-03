package com.emazon.user.infraestructure.rest;


import com.emazon.user.application.services.IAuthenticationService;
import com.emazon.user.domain.model.AuthToken;
import com.emazon.user.domain.model.UserAuthentication;
import com.emazon.user.application.dto.rest.dto.request.authentication.AuthenticationRequestDto;
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

    @PostMapping
    public ResponseEntity<AuthToken> authenticateUser(@RequestBody @Valid AuthenticationRequestDto authenticationRequest) throws Exception {
        UserAuthentication userAuthentication = new UserAuthentication(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        AuthToken authToken = authenticationService.authenticateUser(userAuthentication);

        return new ResponseEntity<>(authToken, HttpStatus.OK);
    }


}
