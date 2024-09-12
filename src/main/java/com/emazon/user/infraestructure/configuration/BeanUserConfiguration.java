package com.emazon.user.infraestructure.configuration;


import com.emazon.user.application.handlers.IUserRestHandler;
import com.emazon.user.application.handlers.implementations.UserRestHandlerImpl;
import com.emazon.user.application.services.IUserService;
import com.emazon.user.application.services.implementations.UserServiceImpl;
import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.domain.usecases.UserUseCasesImpl;
import com.emazon.user.domain.usecases.validators.UserValidator;
import com.emazon.user.domain.ports.out.security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import com.emazon.user.infraestructure.adapters.UserRepositoryMySQLAdapter;
import com.emazon.user.infraestructure.repositories.UserCrudRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanUserConfiguration {

    @Bean
    public IUserRestHandler userRestHandler(IUserService userService){
        return new UserRestHandlerImpl(userService);
    }

    @Bean
    public UserServiceImpl userService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort, UserValidator userValidator){
        return new UserServiceImpl(new UserUseCasesImpl(passwordEncoderPort,userValidator, userRepositoryPort) );
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserCrudRepositoryMySQL userCrudRepositoryMySQL){
        return new UserRepositoryMySQLAdapter(userCrudRepositoryMySQL);
    }

    @Bean
    public UserValidator userValidator(UserRepositoryPort userRepositoryPort, RoleRepositoryPort roleRepositoryPort){
        return new UserValidator(userRepositoryPort, roleRepositoryPort);
    }




}
