package com.unitech.classapi.infrastructure.db.mongodb.model;

import com.unitech.classapi.domain.entity.PendingUser;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@Document(collection = "pending_users")
public class PendingUserModel {
    @Id
    public UUID id;
    private String name;
    private String username;
    private String password_hash;
    private String email;
    private String role;
    private String status;

    public static PendingUserModel toModel(PendingUser user){
        return PendingUserModel.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .password_hash(user.getPassword_hash())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .status(user.getStatus().toString())
                .build();

    }
}
