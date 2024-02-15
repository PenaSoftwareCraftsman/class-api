package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;
@Data
@Builder
public class PendentUserDto {
    public UUID id;
    private String name;
    private String password_hash;
    private String email;
    private Role role;

    private Status status;

    public static PendentUserDto toDto(@NotNull PendingUser pendingUser){
        return PendentUserDto.builder()
                .id(pendingUser.getId())
                .name(pendingUser.getName())
                .password_hash(pendingUser.getPassword())
                .email(pendingUser.getEmail())
                .role(pendingUser.getRole())
                .status(pendingUser.getStatus())
                .build();
    }

    public static List<PendentUserDto> toDto(List<PendingUser> pendingUsers){
        return pendingUsers.stream().map(PendentUserDto::toDto).toList();
    }
}
