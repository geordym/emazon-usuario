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
import com.emazon.user.infraestructure.adapters.RoleRepositoryMySQLAdapter;
import com.emazon.user.infraestructure.adapters.UserRepositoryMySQLAdapter;
import com.emazon.user.infraestructure.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.emazon.user.domain.util.Constantes.MINIMUM_USER_AGE;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserUseCaseImplTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @Mock
    private RoleRepositoryPort roleRepositoryPort;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserUseCasesImpl userUseCases;

    private Role roleWareHouseAssistant;

    private String validEmail = "test@gmail.com";
    private String validPassword = "TestPassword123#";
    private String validPhoneNumber = "+573026468094";

    @BeforeEach
    void setup(){
        userValidator = new UserValidator(mock(UserRepositoryPort.class), mock(RoleRepositoryPort.class));
        roleWareHouseAssistant = new Role(RoleEnum.WAREHOUSE_ASSISTANT.getId(), RoleEnum.WAREHOUSE_ASSISTANT.getName());
    }

    @Test
    public void createUser_WhenCalledWithValidUser_ValidatesAndCreatesUser() {
        User user = new User();
        user.setEmail("valid@example.com");
        user.setPassword("ValidPassword123");
        user.setRole(new Role(1L, "USER_ROLE"));
        user.setBirthDate(LocalDate.now().minusYears(30));
        user.setPhoneNumber("+1234567890");

        when(userRepositoryPort.existsUserByEmail(user.getEmail())).thenReturn(false);
        when(roleRepositoryPort.existsRolById(user.getRole().getId())).thenReturn(true);
        when(userRepositoryPort.createUser(user)).thenReturn(user);

        // Verifica que el método validate en UserValidator es llamado
        userUseCases.createUser(user);

        verify(userValidator).validate(user); // Verifica la interacción con UserValidator
    }



    @Test
    public void getUsersByRoleId_WhenCalled_ReturnsListUser(){
        Long rolId = 10L;
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setEmail("valid@example.com");
        user.setPassword("ValidPassword123");
        user.setRole(new Role(1L, "USER_ROLE"));
        user.setBirthDate(LocalDate.now().minusYears(30));
        user.setPhoneNumber("+1234567890");
        users.add(user);

        when(userUseCases.getUsersByRoleId(rolId)).thenReturn(users);

        List<User> usersReturn = userUseCases.getUsersByRoleId(rolId);

        assertEquals(usersReturn, users);
        assertEquals(usersReturn.get(0).getEmail(), user.getEmail());
    }


    @Test
    public void getUser_WhenCalled_ReturnsOptionalUser(){
        User user = new User();
        user.setEmail("valid@example.com");
        user.setPassword("ValidPassword123");
        user.setRole(new Role(1L, "USER_ROLE"));
        user.setBirthDate(LocalDate.now().minusYears(30));
        user.setPhoneNumber("+1234567890");

        when(userUseCases.getUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Optional<User> userOptional = userUseCases.getUserByEmail(user.getEmail());

        assertNotNull(userOptional);
        assertNotEquals(userOptional, Optional.empty());
        assertEquals(userOptional.get().getEmail(), user.getEmail());
    }


    public static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("The birthDate cannot be null");
        }

        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Test
    public void calculateAge_WhenNullAge_ReturnsException(){
        LocalDate localDate = null;

        assertThrows(IllegalArgumentException.class, () -> {
           UserValidator.calculateAge(localDate);
        });
    }

    @Test
    public void calculateAge_WhenCorrectAge_ReturnsBoolean(){
        Integer yearsPeople = 18;
        LocalDate localDate = LocalDate.now().minusYears(18);

        int ageReturned = UserValidator.calculateAge(localDate);


        assertEquals(ageReturned, yearsPeople);
    }

    @Test
    public void getClientById_WhenCalled_ReturnsOptionalUser(){
        Long clientId = 1L;
        User user = new User();
        user.setId(clientId);
        user.setEmail("valid@example.com");
        user.setPassword("ValidPassword123");
        user.setRole(new Role(1L, "USER_ROLE"));
        user.setBirthDate(LocalDate.now().minusYears(30));
        user.setPhoneNumber("+1234567890");

        when(userUseCases.getClientById(clientId)).thenReturn(Optional.of(user));
        Optional<User> userOptional = userUseCases.getClientById(user.getId());

        assertNotNull(userOptional);
        assertNotEquals(userOptional, Optional.empty());
        assertEquals(userOptional.get().getEmail(), user.getEmail());
    }


    @Test
    public void createUser_WhenCalledWithUsernameEmpty_ReturnsException(){
        User user = new User();
        user.setEmail("");
        user.setPassword(validPassword);
        user.setRole(roleWareHouseAssistant);

        assertThrows(InvalidEmailFormatException.class, () -> {
            userValidator.validate(user);
        });
    }







    @Test
    public void createUser_WhenCalledWithPasswordEmpty_ReturnsException(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword("");
        user.setRole(roleWareHouseAssistant);

        assertThrows(IllegalArgumentException.class, () -> {
            userValidator.validate(user);
        });

    }

    @Test
    public void createUser_WhenCalledWithoutRol_ReturnsException(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword(validPassword);
        //user.setRole(roleWareHouseAssistant);

        assertThrows(IllegalArgumentException.class, () -> {
            userValidator.validate(user);
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
            userValidator.validate(user);
        });

    }

    @Test
    public void createUser_WhenCalledWithInvalidPassword_ReturnsException(){
        User user = new User();
        user.setEmail(validEmail);
        user.setPassword("as");
        user.setRole(roleWareHouseAssistant);

        assertThrows(PasswordFormatException.class, () -> {
            userValidator.validate(user);
        });
    }

}
