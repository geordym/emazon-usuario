package com.emazon.usuario.infraestructure.configuration;

import com.emazon.stock.domain.puertos.out.RoleRepositoryPort;
import com.emazon.stock.infraestructure.adapters.RoleRepositoryMySQLAdapter;
import com.emazon.stock.infraestructure.repositories.RoleCrudRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanRoleConfiguration {

    @Bean
    public RoleRepositoryPort roleRepositoryPort(RoleCrudRepositoryMySQL roleCrudRepositoryMySQL){
        return new RoleRepositoryMySQLAdapter(roleCrudRepositoryMySQL);
    }


}
