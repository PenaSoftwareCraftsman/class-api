package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import lombok.*;

import java.util.*;

@Data
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@Builder
public class ApproveUserResponse {
    public UUID id;
    private String name;
    private String password;
    private String email;
    private Role role;

    public static ApproveUserResponse toDto(User user){
        return ApproveUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
