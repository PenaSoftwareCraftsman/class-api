package com.unitech.classapi.domain;

import com.unitech.classapi.domain.entity.Password;
import com.unitech.classapi.domain.entity.PendingUser;
import com.unitech.classapi.domain.enums.Role;
import com.unitech.classapi.domain.enums.Status;
import com.unitech.classapi.infrastructure.db.mongodb.model.PendingUserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PendingUserTest {
    @Test()
    @DisplayName("Should convert an Entity to Model")
    void shouldConvertEntityToModel(){
        PendingUser pendingUser = new PendingUser(UUID.randomUUID(), "John Doe", Password.create("password"), "johndoe@email.com", Role.TEACHER, Status.PENDING);

        PendingUserModel pendingUserModel = PendingUserModel.toModel(pendingUser);
        assertEquals(pendingUser.getId(), pendingUserModel.getId());
        assertEquals(pendingUser.getName(), pendingUserModel.getName());
        assertEquals(pendingUser.getPassword(), pendingUserModel.getPassword_hash());
        assertEquals(pendingUser.getEmail(), pendingUserModel.getEmail());
        assertEquals(pendingUser.getStatus().toString(), pendingUserModel.getStatus());
        assertEquals(pendingUser.getRole().toString(), pendingUserModel.getRole());
    }
}
