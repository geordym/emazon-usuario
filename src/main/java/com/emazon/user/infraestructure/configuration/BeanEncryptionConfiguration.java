package com.emazon.usuario.infraestructure.configuration;


import com.emazon.usuario.domain.ports.out.Security.PasswordEncoderPort;
import com.emazon.usuario.infraestructure.adapters.Security.EncryptionBCryptAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanEncryptionConfiguration {

    @Bean
    public PasswordEncoderPort passwordEncoderPort(BCryptPasswordEncoder bCryptPasswordEncoder){
        return new EncryptionBCryptAdapter(bCryptPasswordEncoder);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
