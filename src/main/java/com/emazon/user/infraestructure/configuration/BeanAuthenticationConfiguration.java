package com.emazon.user.infraestructure.configuration;

import com.emazon.user.application.services.IAuthenticationService;
import com.emazon.user.application.services.implementations.AuthenticationService;
import com.emazon.user.domain.ports.in.AuthenticationUseCases;
import com.emazon.user.domain.ports.out.Security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.Security.TokenProviderPort;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import com.emazon.user.domain.usecases.AuthenticationUseCasesImpl;
import com.emazon.user.infraestructure.adapters.security.JwtIOTokenAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAuthenticationConfiguration {
    @Bean
    public TokenProviderPort tokenProviderPort(){
        return new JwtIOTokenAdapter();
    }

    @Bean
    public AuthenticationUseCases authenticationUseCases(PasswordEncoderPort passwordEncoderPort,
                                                         UserRepositoryPort userRepositoryPort,
                                                         TokenProviderPort tokenProviderPort
                                                         ){
        return new AuthenticationUseCasesImpl(passwordEncoderPort,userRepositoryPort,tokenProviderPort);
    }

    @Bean
    public IAuthenticationService authenticationService(AuthenticationUseCases authenticationUseCases){
        return new AuthenticationService(authenticationUseCases);
    }

}
