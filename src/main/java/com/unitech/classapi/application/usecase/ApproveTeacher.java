package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.exceptions.*;
import com.unitech.classapi.application.port.*;
import com.unitech.classapi.domain.entity.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApproveTeacher {

    private final UserPort userPort;
    private final Logger logger = LoggerFactory.getLogger(CreatePendingUser.class);

    public User execute(UUID id){
        User user = this.userPort.findUserById(id);

        if(user != null){
            throw new UserAlreadyApprovedException("User already approved");
        }

        return this.userPort.save(id);
    }

}
