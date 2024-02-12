package com.unitech.classapi.infrastructure.http.controller;

import com.unitech.classapi.application.usecase.CreatePendingUser;
import com.unitech.classapi.domain.entity.PendingUser;
import com.unitech.classapi.infrastructure.http.dtos.*;
import jakarta.validation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    CreatePendingUser createPendingUser;

    @PostMapping("/register")
    public ResponseEntity<PendentUserDto> register(
            @RequestBody @Valid NewPendingUserRequestDTO newPendingUser
    ){

        PendingUser pendingUser = createPendingUser.execute(newPendingUser.toDomain());

        return ResponseEntity.ok(PendentUserDto.toDto(pendingUser));
    }

}
