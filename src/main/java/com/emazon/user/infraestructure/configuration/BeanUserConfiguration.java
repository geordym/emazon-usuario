package com.emazon.user.infraestructure.configuration;


import com.emazon.user.application.handlers.IUserRestHandler;
import com.emazon.user.application.handlers.implementations.UserRestHandlerImpl;
import com.emazon.user.application.services.IUserService;
import com.emazon.user.application.services.implementations.UserServiceImpl;
import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.domain.usecases.UserUseCasesImpl;
import com.emazon.user.domain.usecases.validators.UserValidator;
import com.emazon.user.domain.ports.out.security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.UserPersistencePort;
import com.emazon.user.infraestructure.adapters.UserPersistenceMySQLAdapter;
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
    public UserServiceImpl userService(UserPersistencePort userRepositoryPort, PasswordEncoderPort passwordEncoderPort, UserValidator userValidator){
        return new UserServiceImpl(new UserUseCasesImpl(passwordEncoderPort,userValidator, userRepositoryPort) );
    }

    @Bean
    public UserPersistencePort userRepositoryPort(UserCrudRepositoryMySQL userCrudRepositoryMySQL){
        return new UserPersistenceMySQLAdapter(userCrudRepositoryMySQL);
    }

    @Bean
    public UserValidator userValidator(UserPersistencePort userRepositoryPort, RoleRepositoryPort roleRepositoryPort){
        return new UserValidator(userRepositoryPort, roleRepositoryPort);
    }




}
