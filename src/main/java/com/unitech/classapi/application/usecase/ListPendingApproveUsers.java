package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.factory.UserFactory;
import com.unitech.classapi.application.port.PendingUserPort;
import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.PendingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListPendingApproveUsers {
    private final PendingUserPort userPort;

    public List<PendingUser> execute(){
        List<PendingUser> response = userPort.fetchListOfPendingApprovalUsers();

        return response.stream()
                .map(user -> UserFactory.buildPendingUser(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole(),
                        user.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
