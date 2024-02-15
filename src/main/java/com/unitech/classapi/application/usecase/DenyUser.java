package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.exceptions.NewUserRegistrationNotFoundedException;
import com.unitech.classapi.application.exceptions.UserAlreadyApprovedException;
import com.unitech.classapi.application.exceptions.UserAlreadyDeniedException;
import com.unitech.classapi.application.port.PendingUserPort;
import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.PendingUser;
import com.unitech.classapi.domain.entity.User;
import com.unitech.classapi.domain.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DenyUser {
    private final PendingUserPort pendingUserPort;
    private final UserPort userPort;
    public PendingUser execute(UUID id){
        User user = this.userPort.findUserById(id);

        if(user != null) throw new UserAlreadyApprovedException("User already approved");
        PendingUser pendingUser = this.pendingUserPort.fetchPendingUserById(id);
        if(pendingUser == null) throw new NewUserRegistrationNotFoundedException("User registration not founded");
        if(pendingUser.getStatus() == Status.DENIED) throw new UserAlreadyDeniedException("User already denied");

        return this.pendingUserPort.deny(id);
    }
}
