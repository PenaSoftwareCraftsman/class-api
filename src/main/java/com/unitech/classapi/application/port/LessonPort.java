package com.unitech.classapi.application.port;

import com.unitech.classapi.domain.entity.Lesson;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonPort {
    Lesson save(Lesson lesson);
}
