package com.unitech.classapi.infrastructure.http.controller;


import com.unitech.classapi.application.factory.*;
import com.unitech.classapi.config.testcontainer.*;
import com.unitech.classapi.domain.enums.*;
import com.unitech.classapi.infrastructure.db.mongodb.model.*;
import com.unitech.classapi.infrastructure.http.controller.auth.*;
import com.unitech.classapi.infrastructure.http.dtos.*;
import com.unitech.classapi.util.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.*;

import static com.unitech.classapi.util.TestDataBuilder.default_password;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestContainerConfig.class)
public class AuthenticationControllerIT {
    @Autowired
    private ScenePersistBuilder scenePersistBuilder;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        scenePersistBuilder.resetDatabase();
    }
    @Test
    @DisplayName("Should login")
    void testLogin() throws Exception {
        var user = scenePersistBuilder.buildValidSecretaryUser();
        AuthenticationDTO authenticationDTO = new AuthenticationDTO(user.getEmail(), default_password);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authenticationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").exists());

    }

    @Test
    @DisplayName("Should register")
    void testRegister() throws Exception {

        NewPendingUserRequestDTO newPendingUserRequestDTO = TestDataBuilder.generateNewPendingUserRequestDTO();
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newPendingUserRequestDTO)))
                .andExpect(status().isCreated());

        var paddingUserSaved = scenePersistBuilder.userPendingDbRepository.findOne(
                Example.of(
                        PendingUserModel.builder()
                                .email(newPendingUserRequestDTO.getEmail())
                                .build()
                )
        ).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        assertAll(
                () -> Assertions.assertNotNull(paddingUserSaved),
                () -> assertEquals(newPendingUserRequestDTO.getEmail(), paddingUserSaved.getEmail()),
                () -> assertEquals(UserRole.TEACHER.name(), paddingUserSaved.getRole()),
                () -> assertFalse(paddingUserSaved.getPassword_hash().isEmpty()),
                () -> assertEquals(UserStatus.PENDING, paddingUserSaved.getStatus()),
                () -> assertEquals(newPendingUserRequestDTO.getName(), paddingUserSaved.getName())
        );
    }



    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
