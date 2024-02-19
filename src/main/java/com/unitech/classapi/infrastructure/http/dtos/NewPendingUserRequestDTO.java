package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.application.factory.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class NewPendingUserRequestDTO {

    private String name;

    private String password;
    @Email
    private String email;
    private UserRole role;


    public PendingUser toDomain() {
        return UserFactory.buildNewPendingUser(name, email, password, role, UserStatus.PENDING);
    }
}
