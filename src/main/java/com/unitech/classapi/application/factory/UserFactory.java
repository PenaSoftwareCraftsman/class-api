package com.unitech.classapi.application.factory;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.Role;
import com.unitech.classapi.domain.enums.Status;
import java.util.UUID;

public class UserFactory {

    public static PendingUser buildPendingUser(UUID id, String name, String email, String password, Role role, Status status){
        UUID userId = id == null ? UUID.randomUUID() : id;

        Password passwordHash = Password.create(password);

        return PendingUser.builder()
                .id(userId)
                .name(name)
                .email(email)
                .password(passwordHash)
                .role(role)
                .status(status)
                .build();
    }

    public static User buildUser(UUID id, String name, String password, String email, Role role){
        return switch (role) {
            case TEACHER -> createTeacher(id, name, password, email);
            case SECRETARY -> createSecretary(id, name, password, email);
        };
    }

    public static Secretary createSecretary(UUID id, String name, String password, String email){
        return Secretary.builder().id(id).name(name).password(password).email(email).build();
    }

    private static Teacher createTeacher(UUID id, String name, String password, String email){
        return Teacher.builder().id(id).name(name).password(password).email(email).build();
    }

}