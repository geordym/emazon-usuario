package com.emazon.user.infraestructure.configuration;


import com.emazon.user.domain.ports.out.security.PasswordEncoderPort;
import com.emazon.user.infraestructure.adapters.security.EncryptionBCryptAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanSecurityConfiguration {
    @Bean
    public PasswordEncoderPort passwordEncoderPort(BCryptPasswordEncoder bCryptPasswordEncoder){
        return new EncryptionBCryptAdapter(bCryptPasswordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
