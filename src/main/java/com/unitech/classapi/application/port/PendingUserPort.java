package com.unitech.classapi.application.port;

import com.unitech.classapi.domain.entity.PendingUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PendingUserPort {

    void save(PendingUser pendingUser);

    PendingUser fetchByEmail(String email);

    PendingUser fetchPendingUserById(UUID id);

    List<PendingUser> fetchListOfPendingApprovalUsers();

    List<PendingUser> fetchListOfDeniedUsers();

    void deny(UUID id);

    void approve(UUID id);
}
