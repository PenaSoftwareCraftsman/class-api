package com.unitech.classapi.infrastructure.http.dtos;


import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Getter
@AllArgsConstructor
public class AuthenticationDTO {
    @Email
    private String email;
    private String password;
}
