package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.domain.entity.Token;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Getter
@AllArgsConstructor
@Builder
public class AuthenticationResponseDTO {
    private String value;

    public static AuthenticationResponseDTO toDTO(Token token){
        return AuthenticationResponseDTO.builder()
                .value(token.getValue())
                .build();
    }
}


