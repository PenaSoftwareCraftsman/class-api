package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.PendingUser;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePendingUser {

    private final UserPort userPort;

    private final Logger logger = LoggerFactory.getLogger(CreatePendingUser.class);

    public PendingUser execute(PendingUser pendingUser){

        verifyUserExistByEmail(pendingUser.getEmail());

        return this.userPort.save(pendingUser);
    }

    private void verifyUserExistByEmail(String email){
        if(this.userPort.getByEmail(email) != null){
            logger.info("User already exist.");
            throw new RuntimeException("User email already exist");
        };
    }

}
