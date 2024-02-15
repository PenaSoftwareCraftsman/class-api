package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import lombok.*;

import java.util.*;

@Data
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
public class UserDto {
    public UUID id;
    private String name;
    private String password_hash;
    private String email;
    private Role role;
}
