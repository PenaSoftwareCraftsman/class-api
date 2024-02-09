package com.unitech.classapi.application.port;

import com.unitech.classapi.domain.entity.PendingUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPort {
    PendingUser save(PendingUser pendingUser);
}
