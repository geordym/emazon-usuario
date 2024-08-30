package com.emazon.user.infraestructure.configuration;


import com.emazon.user.application.services.implementations.UserService;
import com.emazon.user.domain.usecases.UserUseCasesImpl;
import com.emazon.user.domain.usecases.validators.UserValidator;
import com.emazon.user.domain.ports.out.Security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import com.emazon.user.infraestructure.adapters.UserRepositoryMySQLAdapter;
import com.emazon.user.infraestructure.repositories.UserCrudRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanUserConfiguration {

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort, UserValidator userValidator){
        return new UserService(new UserUseCasesImpl(passwordEncoderPort,userValidator, userRepositoryPort) );
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserCrudRepositoryMySQL userCrudRepositoryMySQL){
        return new UserRepositoryMySQLAdapter(userCrudRepositoryMySQL);
    }




}
