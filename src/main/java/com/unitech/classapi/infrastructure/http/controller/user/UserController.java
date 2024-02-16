package com.unitech.classapi.infrastructure.http.controller.user;

import com.unitech.classapi.application.usecase.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.infrastructure.http.dtos.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ApproveTeacher approveUser;

    @Autowired
    private DenyUser denyUser;

    @Autowired ListPendingApproveUsers listPendingApproveUsers;


    @PutMapping("/approve/{id}")
    public ResponseEntity<ApproveUserResponse> approve(
            @PathVariable("id") UUID id
    ){
        ApproveUserResponse user = ApproveUserResponse.toDto(approveUser.execute(id));

        return ResponseEntity.ok(user);
    }

    @GetMapping("/pendent/list")
    public ResponseEntity<List<PendingUserDto>> fetchPendingApproval(){
        List<PendingUser> listPendingApprovalUsers = listPendingApproveUsers.execute();

        return ResponseEntity.ok(PendingUserDto.toDto(listPendingApprovalUsers));
    }

    @PutMapping("/deny/{id}")
    public ResponseEntity<PendingUserDto> deny(
            @PathVariable("id") UUID id
    ){
        PendingUserDto user = PendingUserDto.toDto(denyUser.execute(id));

        return ResponseEntity.ok(user);
    }
}
