package com.unitech.classapi.infrastructure.db.mongodb.repository;

import com.unitech.classapi.infrastructure.db.mongodb.model.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserDbRepository extends MongoRepository<UserModel, UUID> {
}
