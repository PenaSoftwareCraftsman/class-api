package com.unitech.classapi.infrastructure.db.mongodb.adapter;

import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.Status;
import com.unitech.classapi.infrastructure.db.mongodb.model.*;
import com.unitech.classapi.infrastructure.db.mongodb.repository.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserDBAdapter implements UserPort {

    @Autowired
    private UserPendingDbRepository pendingUser;

    @Autowired
    private UserDbRepository user;

    private final Logger logger = LoggerFactory.getLogger(UserDBAdapter.class);
    @Override
    public PendingUser savePendingUser(PendingUser pendingUser){

        PendingUserModel pendingUserModel = this.pendingUser.save(PendingUserModel.toModel(pendingUser));
        return pendingUserModel.toDomain();

    }

    @Override
    public User findUserById(UUID id){
        UserModel user = this.user.findOne(
                Example.of(UserModel
                        .toModel(Teacher.builder()
                                .id(id)
                                .build()
                        )
                )
                )
                .orElse(null);

        return user != null ? user.toDomain() : null;
    }

    @Override
    public PendingUser fetchByEmail(String email){
        logger.info("Fetching user by email :" + email);
        PendingUserModel pendingUserModel = this.pendingUser.findOne(Example.of(
                PendingUserModel.builder().email(email).build()
            )
        ).orElse(null);

        return pendingUserModel != null ? pendingUserModel.toDomain() : null;
    }

    @Override
    public PendingUser fetchPendingUserById(UUID id){
        logger.info("Fetching user by id :" + id);
        PendingUserModel pendingUserModel = this.pendingUser.findOne(Example.of(
                        PendingUserModel.builder().id(id).build()
                )
        ).orElse(null);

        return pendingUserModel != null ? pendingUserModel.toDomain() : null;
    }

    @Override
    public List<PendingUser> fetchListOfPendingApprovalUsers(){
        List<PendingUserModel> response = this.pendingUser.findAll(Example.of(
                PendingUserModel.builder().status(Status.PENDING.toString()).build()
        ));

        if(response.isEmpty()) return Collections.emptyList();

        return response.stream().map(PendingUserModel::toDomain).collect(Collectors.toList());
    }
    @Override
    public User save(UUID id){
        logger.info("Creating new user ");
        PendingUserModel pendingUserModel = this.pendingUser.findById(id).orElse(null);

        return pendingUserModel == null ? null : this.user.save(pendingUserModel.toRegularUserModel()).toDomain();

    }

    @Override
    public PendingUser deny(UUID id){
        PendingUserModel pendingUserModel = this.pendingUser.findById(id).orElse(null);
        if(pendingUserModel != null){
            pendingUserModel.setStatus("DENIED");
            return this.pendingUser.save(pendingUserModel).toDomain();
        }
        return null;
    }

}
