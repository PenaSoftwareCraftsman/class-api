package com.unitech.classapi.infrastructure.http.controller.user;

import com.unitech.classapi.application.usecase.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.infrastructure.http.dtos.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ApproveUser approveUser;

    @Autowired ListDeniedUsers listDeniedUsers;

    @Autowired
    private DenyUser denyUser;

    @Autowired ListPendingApproveUsers listPendingApproveUsers;


    @PutMapping("/approve/{id}")
    @CacheEvict(value={"pending-approval-list", "denied-list"}, allEntries = true)
    public ResponseEntity<ApproveUserResponse> approve(
            @PathVariable("id") @Valid UUID id
    ){
        approveUser.execute(id);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/pendent/list")
    @Cacheable(value = "pending-approval-list")
    public ResponseEntity<List<PendingUserDto>> fetchPendingApproval(){
        List<PendingUser> listPendingApprovalUsers = listPendingApproveUsers.execute();

        return ResponseEntity.ok(PendingUserDto.toDto(listPendingApprovalUsers));
    }


    @GetMapping("/denied")
    @Cacheable(value = "denied-list")
    public ResponseEntity<List<PendingUserDto>> fetchDeniedList(){
        List<PendingUser> deniedUserList = listDeniedUsers.execute();

        return ResponseEntity.ok(PendingUserDto.toDto(deniedUserList));
    }

    @PutMapping("/deny/{id}")
    @CacheEvict(value = {"denied-list", "pending-approval-list"}, allEntries = true)
    public ResponseEntity<Void> deny(
            @PathVariable("id") @Valid UUID id
    ){
        denyUser.execute(id);
        return ResponseEntity.ok().build();
    }
}
