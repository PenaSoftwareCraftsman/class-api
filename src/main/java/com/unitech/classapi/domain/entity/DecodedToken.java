package com.unitech.classapi.domain.entity;

import com.unitech.classapi.domain.enums.UserRole;
import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@Builder
public class DecodedToken {
    private UUID id;
    private String name;
    private String email;
    private UserRole role;

}
