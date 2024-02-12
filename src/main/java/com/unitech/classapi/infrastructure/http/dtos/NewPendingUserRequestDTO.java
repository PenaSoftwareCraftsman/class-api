package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.application.factory.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
public class NewPendingUserRequestDTO {
    private String name;
    private String password_hash;
    @Email
    private String email;
    private Role role;


    public PendingUser toDomain(){
        return (PendingUser) UserFactory.buildPendingUser(null, name, password_hash, email, role, Status.PENDING);
    }
}
