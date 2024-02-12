package com.unitech.classapi.application.factory;

import com.unitech.classapi.domain.entity.PendingUser;
import com.unitech.classapi.domain.enums.Role;
import com.unitech.classapi.domain.enums.Status;
import com.unitech.classapi.infrastructure.security.utils.*;

import java.util.UUID;

public class UserFactory {

    public static PendingUser buildPendingUser(UUID id, String name, String email, String passwordHash, Role role, Status status){
        return PendingUser.builder()
                .id(id)
                .name(name)
                .email(email)
                .password_hash(SecurityConfig.encodePassword(passwordHash))
                .role(role)
                .status(status)
                .build();

    }

}