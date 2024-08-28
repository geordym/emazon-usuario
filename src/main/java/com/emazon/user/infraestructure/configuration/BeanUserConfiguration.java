package com.emazon.usuario.infraestructure.configuration;


import com.emazon.stock.application.services.UserService;
import com.emazon.stock.application.usecases.UserImpl.CreateUserUseCaseImpl;
import com.emazon.stock.application.validators.UserValidator;
import com.emazon.stock.domain.puertos.out.Security.PasswordEncoderPort;
import com.emazon.stock.domain.puertos.out.UserRepositoryPort;
import com.emazon.stock.infraestructure.adapters.UserRepositoryMySQLAdapter;
import com.emazon.stock.infraestructure.repositories.UserCrudRepositoryMySQL;
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
