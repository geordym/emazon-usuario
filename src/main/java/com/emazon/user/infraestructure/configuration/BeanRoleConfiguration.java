package com.emazon.user.infraestructure.configuration;

import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.infraestructure.adapters.RoleRepositoryMySQLAdapter;
import com.emazon.user.infraestructure.repositories.RoleCrudRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanRoleConfiguration {

    @Bean
    public RoleRepositoryPort roleRepositoryPort(RoleCrudRepositoryMySQL roleCrudRepositoryMySQL){
        return new RoleRepositoryMySQLAdapter(roleCrudRepositoryMySQL);
    }


}
