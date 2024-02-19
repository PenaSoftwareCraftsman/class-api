package com.unitech.classapi.domain.entity;

import com.unitech.classapi.domain.enums.UserRole;
import com.unitech.classapi.domain.enums.UserStatus;
import lombok.*;

import java.util.UUID;

@Data
@Getter
@AllArgsConstructor
@Builder
public class PendingUser {

    public UUID id;
    private String name;
    private Password password;
    private String email;
    private UserRole role;
    private UserStatus status;

    public String getPassword(){
        return password.getContent();
    }
}
