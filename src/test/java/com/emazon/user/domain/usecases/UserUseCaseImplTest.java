package com.emazon.user.domain.usecases;

import com.emazon.user.domain.exception.User.InvalidEmailFormatException;
import com.emazon.user.domain.usecases.validators.UserValidator;
import com.emazon.user.domain.exception.Role.RoleNotFoundException;
import com.emazon.user.domain.exception.User.PasswordFormatException;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.domain.ports.out.security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import com.emazon.user.infraestructure.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;

import static com.emazon.user.domain.util.Constantes.MINIMUM_USER_AGE;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserUseCaseImplTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;
    @SpyBean
    private UserValidator userValidator;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;
    @Mock
    private RoleRepositoryPort roleRepositoryPort;

    @InjectMocks
    private UserUseCasesImpl userUseCases;

    private Role roleWareHouseAssistant;

    private String validEmail = "test@gmail.com";
    private String validPassword = "TestPassword123#";
    private String validPhoneNumber = "+573026468094";

    @BeforeEach
    void setup(){
        roleWareHouseAssistant = new Role(RoleEnum.WAREHOUSE_ASSISTANT.getId(), RoleEnum.WAREHOUSE_ASSISTANT.getName());
        userValidator = new UserValidator(userRepositoryPort, roleRepositoryPort);

        userUseCases = new UserUseCasesImpl(passwordEncoderPort,userValidator, userRepositoryPort);
    }

    @Test
    public void createUser_WhenCalledWithValidUser_ReturnsCreatedUser(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword(validPassword);
        user.setRole(roleWareHouseAssistant);
        user.setBirthDate(LocalDate.now().minusYears(MINIMUM_USER_AGE));
        user.setPhoneNumber(validPhoneNumber);

        when(roleRepositoryPort.existsRolById(roleWareHouseAssistant.getId())).thenReturn(true);
        when(userRepositoryPort.createUser(user)).thenReturn(user);

        // Llamar al método del caso de uso
        User createdUser = userUseCases.createUser(user);

        // Verificar el resultado
        assertNotNull(createdUser);
        assertEquals(validEmail, createdUser.getEmail());
        assertEquals(roleWareHouseAssistant, createdUser.getRole());
    }


    @Test
    public void createUser_WhenCalledWithUsernameEmpty_ReturnsException(){
        User user = new User();
        user.setEmail("");
        user.setPassword(validPassword);
        user.setRole(roleWareHouseAssistant);

        assertThrows(InvalidEmailFormatException.class, () -> {
            userUseCases.createUser(user);
        });
    }

    @Test
    public void createUser_WhenCalledWithPasswordEmpty_ReturnsException(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword("");
        user.setRole(roleWareHouseAssistant);

        assertThrows(IllegalArgumentException.class, () -> {
            userUseCases.createUser(user);
        });

    }

    @Test
    public void createUser_WhenCalledWithoutRol_ReturnsException(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword(validPassword);
        //user.setRole(roleWareHouseAssistant);

        assertThrows(IllegalArgumentException.class, () -> {
            userUseCases.createUser(user);
        });

    }

    @Test
    public void createUser_WhenCalledWithRolDoesNotExist_ReturnsException(){

        Role roleFake = new Role(1000L, "PPPPPPPPPPPPPPPPPP");
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword(validPassword);
        user.setRole(roleFake);

        assertThrows(RoleNotFoundException.class, () -> {
            userUseCases.createUser(user);
        });

    }

    @Test
    public void createUser_WhenCalledWithInvalidPassword_ReturnsException(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword("as");
        user.setRole(roleWareHouseAssistant);

        assertThrows(PasswordFormatException.class, () -> {
            userUseCases.createUser(user);
        });
    }

}
