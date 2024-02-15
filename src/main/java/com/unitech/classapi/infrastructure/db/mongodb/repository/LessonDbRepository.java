package com.unitech.classapi.infrastructure.db.mongodb.repository;

import com.unitech.classapi.infrastructure.db.mongodb.model.LessonModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LessonDbRepository extends MongoRepository<LessonModel, UUID> {
}
