package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.exceptions.*;
import com.unitech.classapi.application.port.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.UserStatus;
import lombok.*;
import org.slf4j.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApproveUser {

    private final UserPort userPort;

    private final PendingUserPort pendingUserPort;

    public User execute(UUID id){

        PendingUser pendingUser = this.pendingUserPort.fetchPendingUserById(id);
        if(pendingUser.getStatus() == UserStatus.APPROVED) throw new UserAlreadyApprovedException("User already approved");

        pendingUser.setStatus(UserStatus.APPROVED);

        this.pendingUserPort.save(pendingUser);

        return this.userPort.save(id);
    }

}
