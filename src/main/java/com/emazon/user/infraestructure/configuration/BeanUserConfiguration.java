package com.emazon.user.infraestructure.configuration;


import com.emazon.user.application.services.UserService;
import com.emazon.user.application.usecases.UserImpl.CreateUserUseCaseImpl;
import com.emazon.user.application.validators.UserValidator;
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
        return new UserService(new CreateUserUseCaseImpl(userRepositoryPort, passwordEncoderPort, userValidator));
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserCrudRepositoryMySQL userCrudRepositoryMySQL){
        return new UserRepositoryMySQLAdapter(userCrudRepositoryMySQL);
    }


}
