package com.unitech.classapi.domain.entity;

import com.unitech.classapi.domain.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Secretary extends User {
    @Builder
    public Secretary(UUID id, String name, String password, String email){
        super(id, name, password, email, Role.SECRETARY);
    }
}
