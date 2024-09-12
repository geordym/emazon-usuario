package com.emazon.user.infraestructure.rest;


import com.emazon.user.domain.ports.out.UserRepositoryPort;
import com.emazon.user.domain.ports.out.security.TokenProviderPort;
import com.emazon.user.domain.util.ErrorMessages;
import com.emazon.user.infraestructure.configuration.security.JwtRequestFilter;
import com.emazon.user.infraestructure.configuration.security.MyUserDetailsService;
import com.emazon.user.domain.enums.RoleEnum;
import com.emazon.user.application.dto.rest.dto.request.user.CreateUserRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.emazon.user.domain.util.Constantes.MINIMUM_USER_AGE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserRepositoryPort userRepositoryPort;

    @MockBean
    private TokenProviderPort tokenProviderPort;

    CreateUserRequestDto createUserRequestDtoValid = new CreateUserRequestDto();

    @BeforeEach
    void setup(){

        Faker faker = new Faker();

        createUserRequestDtoValid.setFirstName(faker.name().firstName());
        createUserRequestDtoValid.setLastName(faker.name().lastName());
        createUserRequestDtoValid.setEmail(faker.internet().emailAddress());
        createUserRequestDtoValid.setPassword(faker.internet().password());
        createUserRequestDtoValid.setPhoneNumber("+" + faker.numerify("############"));
        createUserRequestDtoValid.setBirthDate(LocalDate.now().minusYears(MINIMUM_USER_AGE));
        createUserRequestDtoValid.setIdentityDocument(faker.numerify("##########"));
    }


    @Test
    public void When_UserAuxBodegaInformationIsCorrect_Expect_UserCreated() throws Exception {
        // Crea un objeto User de ejemplo
        String userJson = objectMapper.writeValueAsString(createUserRequestDtoValid);

        // Realiza la solicitud POST con el JSON como cuerpo
        mockMvc.perform(post("/api/users/register/warehouse-assistant")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void Expect_RoleNotFoundException_When_RolePassedDoesNotExist() throws Exception {
        String userJson = objectMapper.writeValueAsString(createUserRequestDtoValid);

        // Realiza la solicitud POST con el JSON como cuerpo
        mockMvc.perform(post("/api/users/warehouse-assistant")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isNotFound()) // Verifica que el estado sea 404 Not Found
                .andExpect(jsonPath("$.error").value(String.format(ErrorMessages.ROLE_NOT_FOUND)));
    }

    @Test
    public void When_UserDataIsValidAndRoleExist_Expect_UserCreated() throws Exception {
        String userJson = objectMapper.writeValueAsString(createUserRequestDtoValid);

        mockMvc.perform(post("/api/users/register/client")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void Expect_UsernameAlreadyTaken_When_UsernamePassedAlreadyExist() throws Exception {
        String userJson = objectMapper.writeValueAsString(createUserRequestDtoValid);

        when(userRepositoryPort.existsUserByEmail(createUserRequestDtoValid.getEmail()))
                .thenReturn(true);

        mockMvc.perform(post("/api/users/register/client")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isConflict()) // Verifica que el estado sea 404 Not Found
                .andExpect(jsonPath("$.error").value(String.format(ErrorMessages.EMAIL_ALREADY_TAKEN)));
    }

    @Test
    public void Expect_InvalidEmailFormatException_When_EmailPassedIsInvalid() throws Exception {
        createUserRequestDtoValid.setEmail("aaaaaaaaaaa");
        String userJson = objectMapper.writeValueAsString(createUserRequestDtoValid);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void Expect_IdentityDocumentTakenException_When_IdentityDocumentIsAlreadyRegistered() throws Exception {
        String identityDocument = "1999999999";
        createUserRequestDtoValid.setIdentityDocument(identityDocument);
        String userJson = objectMapper.writeValueAsString(createUserRequestDtoValid);

        when(userRepositoryPort.existsUserByIdentityDocument(identityDocument)).thenReturn(true);


        mockMvc.perform(post("/api/users/client")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value(String.format(ErrorMessages.IDENTITY_DOCUMENT_ALREADY_TAKEN)));

    }

    @Test
    public void Expect_UnderageUserException_When_UserAgeLessThanMinAge() throws Exception {
        LocalDate birthdayDateValid= LocalDate.now().minusYears(MINIMUM_USER_AGE);
        LocalDate birthdayDateInvalid = birthdayDateValid.plusYears(1);
        createUserRequestDtoValid.setBirthDate(birthdayDateInvalid);
        String userJson = objectMapper.writeValueAsString(createUserRequestDtoValid);

        mockMvc.perform(post("/api/users/client")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(String.format(ErrorMessages.UNDERAGE_USER)));

    }

    @Test
    public void WhenUserAgeIsValid_thenUserIsCreatedSuccessfully() throws Exception {
        LocalDate birthdayDateValid= LocalDate.now().minusYears(MINIMUM_USER_AGE);
        createUserRequestDtoValid.setBirthDate(birthdayDateValid);
        String userJson = objectMapper.writeValueAsString(createUserRequestDtoValid);

        mockMvc.perform(post("/api/users/client")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isCreated());
    }






    private static Stream<Long> provideRoleIds() {
        return RoleEnum.getAllIds().stream();
    }

}
