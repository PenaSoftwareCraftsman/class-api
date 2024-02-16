package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.application.factory.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
public class NewPendingUserRequestDTO {

    private String name;

    private String password;
    @Email
    private String email;
    private Role role;


    public PendingUser toDomain() {
        return UserFactory.buildPendingUser(null, name, email, password, role, Status.PENDING);
    }
}
