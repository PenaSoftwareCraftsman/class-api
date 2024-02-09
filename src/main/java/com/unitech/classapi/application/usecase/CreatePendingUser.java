package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.PendingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePendingUser {

    private final UserPort userPort;

    public PendingUser execute(PendingUser pendingUser){
        return this.userPort.save(pendingUser);
    }

}
