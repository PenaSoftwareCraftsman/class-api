package com.unitech.classapi.infrastructure.db.mongodb.repository;

import com.unitech.classapi.infrastructure.db.mongodb.model.PendingUserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPendingDbRepository extends MongoRepository<PendingUserModel, UUID> {
    PendingUserModel findByEmail(String email);
}
