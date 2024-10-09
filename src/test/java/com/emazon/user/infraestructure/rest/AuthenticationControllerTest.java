package com.emazon.user.infraestructure.rest;

import com.emazon.user.application.dto.rest.dto.request.authentication.AuthenticationRequestDto;
import com.emazon.user.domain.ports.out.UserPersistencePort;
import com.emazon.user.domain.ports.out.security.TokenProviderPort;
import com.emazon.user.infraestructure.configuration.security.JwtRequestFilter;
import com.emazon.user.infraestructure.configuration.security.MyUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserPersistencePort userRepositoryPort;

    @MockBean
    private TokenProviderPort tokenProviderPort;

    AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto("bea.hickle@hotmail.com", "admin1234");

    @Test
    public void When_Authenticate_Expect_Error() throws Exception {
        // Crea un objeto User de ejemplo
        String userJson = objectMapper.writeValueAsString(authenticationRequestDto);

        // Realiza la solicitud POST con el JSON como cuerpo
        mockMvc.perform(post("/api/authenticate")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().is4xxClientError());
    }


}
