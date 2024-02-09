package com.unitech.classapi.domain.entity;

import com.unitech.classapi.domain.enums.Role;
import com.unitech.classapi.domain.enums.Status;
import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@Builder
public class PendingUser {

    public UUID id;
    private String name;
    private String username;
    private String password_hash;
    private String email;
    private Role role;
    private Status status;
}
