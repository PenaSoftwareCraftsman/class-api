package com.unitech.classapi.infrastructure.http.controller;

import com.unitech.classapi.config.testcontainer.*;
import com.unitech.classapi.domain.enums.*;
import com.unitech.classapi.infrastructure.db.mongodb.model.*;
import com.unitech.classapi.util.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static com.unitech.classapi.util.TestDataBuilder.generateValidToken;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestContainerConfig.class)
public class UserControllerTestsIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScenePersistBuilder scenePersistBuilder;

    @BeforeEach
    void setUp() {
        scenePersistBuilder.resetDatabase();
    }

    @Test
    @DisplayName("Should approve user")
    void testApproveUser() throws Exception {
        scenePersistBuilder.buildValidSecretaryUser();
        var tokenSecretary = generateValidToken();
        var newPendingUser = scenePersistBuilder.buildValidPendingTeacherUser();
        mockMvc.perform(put("/user/approve/{id}", newPendingUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + tokenSecretary)
                )
                .andExpect(status().isCreated());

        var newTeacherUser = scenePersistBuilder.userDbRepository.findOne(
                Example.of(
                        UserModel.builder().email(newPendingUser.getEmail()).build()
                )
        ).orElseThrow(() -> new RuntimeException("User not found"));

        var pendingUser = scenePersistBuilder.userPendingDbRepository.findOne(
                Example.of(
                        PendingUserModel.builder().id(newPendingUser.getId()).build()
                )
        ).orElseThrow(
                () -> new RuntimeException("Pending user not found")
        );


        assertAll(
                () -> assertEquals(UserRole.TEACHER.name(), newTeacherUser.getRole()),
                () -> assertEquals(newPendingUser.getName(), newTeacherUser.getName()),
                () -> assertEquals(UserStatus.APPROVED, pendingUser.getStatus())
        );
    }

    @Test
    @DisplayName("Should fetch pending approval list")
    void testFetchPendingApproval() throws Exception {
        scenePersistBuilder.buildValidSecretaryUser();
        var tokenSecretary = generateValidToken();
        scenePersistBuilder.buildListValidPendingTeacherUser(5);
        mockMvc.perform(get("/user/pendent/list")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + tokenSecretary)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[4].id").exists());
    }

    @Test
    @DisplayName("Should fetch denied list")
    void testFetchDeniedList() throws Exception {
        scenePersistBuilder.buildValidSecretaryUser();
        var tokenSecretary = generateValidToken();
        scenePersistBuilder.buildListValidDeniedTeacherUser(5);
        mockMvc.perform(get("/user/denied")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + tokenSecretary)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[4].id").exists()); // assuming your JSON response contains an id field
    }

    @Test
    @DisplayName("Should deny user")
    void testDenyUser() throws Exception {
        scenePersistBuilder.buildValidSecretaryUser();
        var tokenSecretary = generateValidToken();
        var newPendingUser = scenePersistBuilder.buildValidPendingTeacherUser();
        mockMvc.perform(put("/user/deny/{id}", newPendingUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + tokenSecretary)
                )
                .andExpect(status().isCreated());
        var pendingUser = scenePersistBuilder.userPendingDbRepository.findOne(
                Example.of(
                        PendingUserModel.builder()
                                .email(newPendingUser.getEmail())
                                .build()
                )
        ).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        assertEquals(UserStatus.DENIED, pendingUser.getStatus());
    }
}
