package com.unitech.classapi.infrastructure.http.controller;

import com.unitech.classapi.application.usecase.CreatePendingUser;
import com.unitech.classapi.domain.entity.PendingUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    CreatePendingUser createPendingUser;

    @PostMapping("/register")
    public ResponseEntity<?> register(){
        PendingUser pendingUser = createPendingUser.execute();
        return ResponseEntity.ok();
    }

}
