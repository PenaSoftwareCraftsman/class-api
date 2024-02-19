package com.unitech.classapi.infrastructure.db.mongodb.model;

import com.unitech.classapi.application.factory.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@Document(collection = "user")
public class UserModel {
    @Id
    public UUID id;
    private String name;
    private String password;
    private String email;
    private String role;

    public static UserModel toModel(User user){
        return UserModel.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .build();

    }

    public User toDomain(){
        return UserFactory.buildUser(this.id, this.name, this.password, this.email, UserRole.valueOf(this.role));
    }
}
