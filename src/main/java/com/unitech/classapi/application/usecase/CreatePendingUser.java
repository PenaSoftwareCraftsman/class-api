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

    public PendingUser execute(PendingUser pendingUser) {
        verifyUserExistByEmail(pendingUser.getEmail());
        return this.userPort.savePendingUser(pendingUser);
    }

    private void verifyUserExistByEmail(String email) {
        if (this.userPort.fetchByEmail(email) != null) {
            logger.info("User already exists.");
            throw new RuntimeException("User email already exists");
        }
    }
}
