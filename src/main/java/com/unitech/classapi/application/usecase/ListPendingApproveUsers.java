package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.factory.UserFactory;
import com.unitech.classapi.application.port.PendingUserPort;
import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.PendingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPendingApproveUsers {
    private final PendingUserPort userPort;

    public List<PendingUser> execute(){
        List<PendingUser> response = userPort.fetchListOfPendingApprovalUsers();

        List<PendingUser> pendingApprovalUserList = new ArrayList<>();

        response.forEach(user -> pendingApprovalUserList.add(
                UserFactory.buildPendingUser(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole(),
                        user.getStatus()
                )
        ));
        return pendingApprovalUserList;
    }
}
