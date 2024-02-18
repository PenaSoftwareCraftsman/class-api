package com.unitech.classapi.infrastructure.db.mongodb.repository;

import com.unitech.classapi.infrastructure.db.mongodb.model.PendingUserModel;
import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPendingDbRepository extends MongoRepository<PendingUserModel, UUID> {

    @Query("{'_id': ?0, 'status': ?1}")
    void setStatus(@NotNull UUID id, @NotNull String status);

}
