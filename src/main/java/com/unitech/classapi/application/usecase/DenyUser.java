package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.exceptions.*;
import com.unitech.classapi.application.port.PendingUserPort;
import com.unitech.classapi.domain.entity.PendingUser;
import com.unitech.classapi.domain.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DenyUser {
    private final PendingUserPort pendingUserPort;
    public void execute(UUID id){

        PendingUser pendingUser = this.pendingUserPort.fetchPendingUserById(id);
        if(pendingUser.getStatus() == UserStatus.APPROVED) throw new UserAlreadyApprovedException("User already approved");
        if(pendingUser.getStatus() == UserStatus.DENIED) throw new UserAlreadyDeniedException("User already denied");

        pendingUserPort.deny(id);
    }
}
