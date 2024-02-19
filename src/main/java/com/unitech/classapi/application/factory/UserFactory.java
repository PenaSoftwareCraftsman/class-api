package com.unitech.classapi.application.factory;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.UserRole;
import com.unitech.classapi.domain.enums.UserStatus;

import java.util.*;

public class UserFactory {

    public static PendingUser buildNewPendingUser (String name, String email, String password, UserRole role, UserStatus status){
        UUID userId =  UUID.randomUUID();

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

    public static PendingUser buildPendingUser(UUID id, String name, String email, String password, UserRole role, UserStatus status){
        return PendingUser.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(new Password(password))
                .role(role)
                .status(status)
                .build();
    }



    public static User buildUser(UUID id, String name, String password, String email, UserRole role){
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