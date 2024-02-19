package com.unitech.classapi.application.factory;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.Role;
import com.unitech.classapi.domain.enums.UserStatus;
import java.util.UUID;

public class UserFactory {

    public static PendingUser buildPendingUser(UUID id, String name, String email, String password, Role role, UserStatus status){
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

    public static User fromPendingUser(PendingUser pendingUser){
        return switch (pendingUser.getRole()) {
            case TEACHER -> createTeacher(pendingUser.getId(), pendingUser.getName(), pendingUser.getPassword(), pendingUser.getEmail());
            case SECRETARY -> createSecretary(pendingUser.getId(), pendingUser.getName(), pendingUser.getPassword(), pendingUser.getEmail());
        };
    }

    public static Secretary createSecretary(UUID id, String name, String password, String email){
        return Secretary.builder().id(id).name(name).password(password).email(email).build();
    }

    private static Teacher createTeacher(UUID id, String name, String password, String email){
        return Teacher.builder().id(id).name(name).password(password).email(email).build();
    }

}