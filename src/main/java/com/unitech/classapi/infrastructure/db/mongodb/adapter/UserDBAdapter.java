package com.unitech.classapi.infrastructure.db.mongodb.adapter;

import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.PendingUser;
import com.unitech.classapi.infrastructure.db.mongodb.model.PendingUserModel;
import com.unitech.classapi.infrastructure.db.mongodb.repository.UserPendingDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDBAdapter implements UserPort {

    @Autowired
    private UserPendingDbRepository pendingUser;
    @Override
    public PendingUser save(PendingUser pendingUser){

        PendingUserModel pendingUserModel = this.pendingUser.save(PendingUserModel.toModel(pendingUser));
        return pendingUserModel.toDomain();

    }

}
