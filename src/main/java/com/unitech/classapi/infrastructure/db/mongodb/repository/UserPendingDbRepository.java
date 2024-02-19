package com.unitech.classapi.infrastructure.db.mongodb.repository;

import com.unitech.classapi.domain.enums.*;
import com.unitech.classapi.infrastructure.db.mongodb.model.PendingUserModel;
import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPendingDbRepository extends MongoRepository<PendingUserModel, UUID> {


    @Query("{'id' : ?0}")
    @Update("{'$set': {'status': ?1}}")
    void updateStatusById(@NotNull UUID id, @NotNull UserStatus status);

}
