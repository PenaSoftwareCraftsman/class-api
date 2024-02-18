package com.unitech.classapi.infrastructure.db.mongodb.adapter;

import com.unitech.classapi.application.port.PendingUserPort;
import com.unitech.classapi.domain.entity.PendingUser;
import com.unitech.classapi.domain.enums.UserStatus;
import com.unitech.classapi.infrastructure.db.mongodb.model.PendingUserModel;
import com.unitech.classapi.infrastructure.db.mongodb.repository.UserPendingDbRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PendingUserDBAdapter implements PendingUserPort {
    @Autowired
    private UserPendingDbRepository pendingUser;

    public PendingUser save(PendingUser pendingUser){

        PendingUserModel pendingUserModel = this.pendingUser.save(PendingUserModel.toModel(pendingUser));
        return pendingUserModel.toDomain();
    }

    @Override
    public PendingUser fetchByEmail(String email){
        PendingUserModel pendingUserModel = this.pendingUser.findOne(Example.of(
                        PendingUserModel.builder().email(email).build()
                )
        ).orElse(null);

        return pendingUserModel != null ? pendingUserModel.toDomain() : null;
    }

    @Override
    public List<PendingUser> fetchListOfPendingApprovalUsers(){
        List<PendingUserModel> response = this.pendingUser.findAll(Example.of(
                PendingUserModel.builder().status(UserStatus.PENDING.toString()).build()
        ));

        if(response.isEmpty()) return Collections.emptyList();

        return response.stream().map(PendingUserModel::toDomain).collect(Collectors.toList());
    }
    @Override
    public PendingUser fetchPendingUserById(UUID id){
        PendingUserModel pendingUserModel = this.pendingUser.findOne(Example.of(
                        PendingUserModel.builder().id(id).build()
                )
        ).orElse(null);

        return pendingUserModel != null ? pendingUserModel.toDomain() : null;
    }

    @Override
    public List<PendingUser> fetchListOfDeniedUsers(){
        List<PendingUserModel> response = this.pendingUser.findAll(Example.of(
                PendingUserModel.builder().status(UserStatus.DENIED.toString()).build()
        ));

        if(response.isEmpty()) return Collections.emptyList();

        return response.stream().map(PendingUserModel::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deny(@NotNull UUID id){
        this.pendingUser.setStatus(id, UserStatus.DENIED.toString());
    }
}
