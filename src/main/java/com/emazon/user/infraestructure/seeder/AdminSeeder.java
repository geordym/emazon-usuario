package com.emazon.user.infraestructure.seeder;

import com.emazon.user.application.services.implementations.UserService;
import com.emazon.user.domain.model.User;
import com.emazon.user.infraestructure.enums.RoleEnum;
import com.emazon.user.infraestructure.factories.UserFactory;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.emazon.user.domain.util.Constantes.MINIMUM_USER_AGE;


@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner, Ordered {

    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(AdminSeeder.class);
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        User userAdmin = UserFactory.createUserModel(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.numerify("############"),
                "+" + 999999999999L,
                faker.internet().emailAddress(),
                "admin",
                LocalDate.now().minusYears(MINIMUM_USER_AGE),
                RoleEnum.ADMINISTRADOR.toModel()
                );

        List<User> administradores =  userService.getUsersByRoleId(RoleEnum.ADMINISTRADOR.getId());
        if (administradores.isEmpty()) {
            log.info("No administrator detected in the database, creating a default administrator.");
            userService.createUser(userAdmin);
        }
        System.out.println("USUARIOS ADMINISTRADORES: " + administradores.size());

    }


    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
