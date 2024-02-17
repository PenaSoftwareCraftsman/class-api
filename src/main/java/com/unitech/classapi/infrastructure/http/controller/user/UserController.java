package com.unitech.classapi.infrastructure.http.controller.user;

import com.unitech.classapi.application.usecase.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.infrastructure.http.dtos.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ApproveTeacher approveUser;

    @Autowired ListDeniedUsers listDeniedUsers;

    @Autowired
    private DenyUser denyUser;

    @Autowired ListPendingApproveUsers listPendingApproveUsers;


    @PutMapping("/approve/{id}")
    @CacheEvict(value={"pending-approval-list", "denied-list"})
    public ResponseEntity<ApproveUserResponse> approve(
            @PathVariable("id") @Valid UUID id
    ){
        ApproveUserResponse user = ApproveUserResponse.toDto(approveUser.execute(id));

        return ResponseEntity.ok(user);
    }

    @GetMapping("/pendent/list")
    @Cacheable("pending-approval-list")
    public ResponseEntity<List<PendingUserDto>> fetchPendingApproval(){
        List<PendingUser> listPendingApprovalUsers = listPendingApproveUsers.execute();

        return ResponseEntity.ok(PendingUserDto.toDto(listPendingApprovalUsers));
    }


    @GetMapping("/denied")
    @Cacheable("denied-list")
    public ResponseEntity<List<PendingUserDto>> fetchDeniedList(){
        List<PendingUser> deniedUserList = listDeniedUsers.execute();

        return ResponseEntity.ok(PendingUserDto.toDto(deniedUserList));
    }

    @PutMapping("/deny/{id}")
    @CacheEvict(value = {"denied-list", "pending-approval-list"})
    public ResponseEntity<PendingUserDto> deny(
            @PathVariable("id") @Valid UUID id
    ){
        PendingUserDto user = PendingUserDto.toDto(denyUser.execute(id));

        return ResponseEntity.ok(user);
    }
}
