package com.unitech.classapi.domain.entity;

import com.unitech.classapi.domain.enums.*;
import lombok.*;

import java.util.*;

@Data
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
public class User {
    public UUID id;
    private String name;
    private String password_hash;
    private String email;
    private Role role;

}
