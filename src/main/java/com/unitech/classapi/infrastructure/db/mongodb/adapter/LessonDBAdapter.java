package com.unitech.classapi.infrastructure.db.mongodb.adapter;

import com.unitech.classapi.application.port.LessonPort;
import com.unitech.classapi.domain.entity.Lesson;
import com.unitech.classapi.infrastructure.db.mongodb.model.LessonModel;
import com.unitech.classapi.infrastructure.db.mongodb.repository.LessonDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LessonDBAdapter implements LessonPort {
    @Autowired
    LessonDbRepository lessonDbRepository;
    @Override
    public Lesson save(Lesson lesson){
        LessonModel lessonModel = this.lessonDbRepository.save(LessonModel.toModel(lesson));
        return lessonModel.toDomain();
    }

    @Override
    public Lesson fetchById(UUID id){
        LessonModel lesson = this.lessonDbRepository.findOne(
                Example.of(LessonModel.toModel(
                        Lesson.builder()
                                .id(id)
                                .build()
                )
            )
        ).orElse(null);

        return lesson != null ? lesson.toDomain() : null;
    }
}
