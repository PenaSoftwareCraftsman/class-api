package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;
@Data
@Builder
public class PendingUserDto {
    public UUID id;
    private String name;
    private String password_hash;
    private String email;
    private Role role;

    private UserStatus status;

    public static PendingUserDto toDto(@NotNull PendingUser pendingUser){
        return PendingUserDto.builder()
                .id(pendingUser.getId())
                .name(pendingUser.getName())
                .password_hash(pendingUser.getPassword())
                .email(pendingUser.getEmail())
                .role(pendingUser.getRole())
                .status(pendingUser.getStatus())
                .build();
    }

    public static List<PendingUserDto> toDto(List<PendingUser> pendingUsers){
        return pendingUsers.stream().map(PendingUserDto::toDto).toList();
    }
}
