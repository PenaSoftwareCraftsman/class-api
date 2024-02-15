package com.unitech.classapi.infrastructure.http.controller;

import com.unitech.classapi.application.usecase.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.infrastructure.http.dtos.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private CreatePendingUser createPendingUser;

    @Autowired
    private ApproveTeacher approveUser;

    @PostMapping("/register")
    public ResponseEntity<PendentUserDto> register(
            @RequestBody @Valid NewPendingUserRequestDTO newPendingUser
    ){

        PendingUser pendingUser = createPendingUser.execute(newPendingUser.toDomain());

        return ResponseEntity.ok(PendentUserDto.toDto(pendingUser));
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<ApproveUserResponse> approve(
            @PathVariable("id") UUID id
    ){
        ApproveUserResponse user = ApproveUserResponse.toDto(approveUser.execute(id));

        return ResponseEntity.ok(user);
    }
}
