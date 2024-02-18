package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.exceptions.*;
import com.unitech.classapi.application.port.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.Status;
import lombok.*;
import org.slf4j.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApproveUser {

    private final UserPort userPort;

    private final PendingUserPort pendingUserPort;

    private final Logger logger = LoggerFactory.getLogger(CreatePendingUser.class);

    public User execute(UUID id){

        PendingUser pendingUser = this.pendingUserPort.fetchPendingUserById(id);
        if(pendingUser == null) throw new NewUserRegistrationNotFoundedException("User registration not founded");
        if(pendingUser.getStatus() == Status.ACCEPTED) throw new UserAlreadyApprovedException("User already approved");

        pendingUser.setStatus(Status.ACCEPTED);

        this.pendingUserPort.save(pendingUser);

        return this.userPort.save(id);
    }

}
