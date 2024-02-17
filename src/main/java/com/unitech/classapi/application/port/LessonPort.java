package com.unitech.classapi.application.port;

import com.unitech.classapi.domain.entity.Lesson;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonPort {
    Lesson save(Lesson lesson);

    Lesson fetchById(UUID id);

    List<Lesson> fetchListByTeacherId(UUID id);
}
