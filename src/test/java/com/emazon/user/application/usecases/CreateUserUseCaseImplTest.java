package com.emazon.user.application.usecases;

import com.emazon.user.application.usecases.UserImpl.CreateUserUseCaseImpl;
import com.emazon.user.application.validators.UserValidator;
import com.emazon.user.domain.exception.Role.RoleEmptyException;
import com.emazon.user.domain.exception.Role.RoleNotFoundException;
import com.emazon.user.domain.exception.User.PasswordEmptyException;
import com.emazon.user.domain.exception.User.PasswordFormatException;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.domain.ports.out.Security.PasswordEncoderPort;
import com.emazon.user.domain.ports.out.UserRepositoryPort;
import com.emazon.user.infraestructure.enums.RoleEnum;
import com.emazon.user.infraestructure.factories.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CreateUserUseCaseImplTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;
    @SpyBean
    private UserValidator userValidator;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;
    @Mock
    private RoleRepositoryPort roleRepositoryPort;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    private Role roleWareHouseAssistant;

    private String validEmail = "test@gmail.com";
    private String validPassword = "TestPassword123#";

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        roleWareHouseAssistant = new Role(RoleEnum.AUX_BODEGA.getId(), RoleEnum.AUX_BODEGA.getName());
        userValidator = new UserValidator(userRepositoryPort, roleRepositoryPort);

        createUserUseCase = new CreateUserUseCaseImpl(userRepositoryPort,passwordEncoderPort,userValidator);
    }

    @Test
    public void createUser_WhenCalledWithValidUser_ReturnsCreatedUser(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword(validPassword);
        user.setRole(roleWareHouseAssistant);

        when(roleRepositoryPort.existsRolById(roleWareHouseAssistant.getId())).thenReturn(true);
        when(userRepositoryPort.createUser(user)).thenReturn(user);

        // Llamar al método del caso de uso
        User createdUser = createUserUseCase.createUser(user);

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

        when(userRepositoryPort.createUser(user)).thenReturn(user);


    }

    @Test
    public void createUser_WhenCalledWithPasswordEmpty_ReturnsException(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword("");
        user.setRole(roleWareHouseAssistant);

        assertThrows(PasswordEmptyException.class, () -> {
            createUserUseCase.createUser(user);
        });

    }

    @Test
    public void createUser_WhenCalledWithoutRol_ReturnsException(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword(validPassword);
        //user.setRole(roleWareHouseAssistant);

        assertThrows(RoleEmptyException.class, () -> {
            createUserUseCase.createUser(user);
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
            createUserUseCase.createUser(user);
        });

    }

    @Test
    public void createUser_WhenCalledWithInvalidPassword_ReturnsException(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword("as");
        user.setRole(roleWareHouseAssistant);

        assertThrows(PasswordFormatException.class, () -> {
            createUserUseCase.createUser(user);
        });
    }

}
