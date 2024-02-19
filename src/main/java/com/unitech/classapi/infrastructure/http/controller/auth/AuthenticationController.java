package com.unitech.classapi.infrastructure.http.controller.auth;

import com.unitech.classapi.application.usecase.CreatePendingUser;
import com.unitech.classapi.application.usecase.LoginUser;
import com.unitech.classapi.domain.entity.PendingUser;
import com.unitech.classapi.infrastructure.http.dtos.AuthenticationDTO;
import com.unitech.classapi.infrastructure.http.dtos.AuthenticationResponseDTO;
import com.unitech.classapi.infrastructure.http.dtos.NewPendingUserRequestDTO;
import com.unitech.classapi.infrastructure.http.dtos.PendingUserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CreatePendingUser createPendingUser;

    @Autowired
    private LoginUser login;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){

            var token = login.execute(data.getEmail(), data.getPassword());

            return ResponseEntity.ok(AuthenticationResponseDTO.toDTO(token));
        }

    @PostMapping("/register")
    @CacheEvict(value = {"denied-list", "pending-approval-list"}, allEntries = true)
    public ResponseEntity<Void> register(
            @RequestBody @Valid NewPendingUserRequestDTO newPendingUser
    ){

        createPendingUser.execute(newPendingUser.toDomain());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
