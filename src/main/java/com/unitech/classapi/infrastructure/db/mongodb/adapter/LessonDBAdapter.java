package com.unitech.classapi.infrastructure.db.mongodb.adapter;

import com.unitech.classapi.application.port.LessonPort;
import com.unitech.classapi.domain.entity.Lesson;
import com.unitech.classapi.infrastructure.db.mongodb.model.LessonModel;
import com.unitech.classapi.infrastructure.db.mongodb.repository.LessonDbRepository;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
                Example.of(LessonModel.builder()
                        .id(id).build()
            )
        ).orElse(null);

        return lesson != null ? lesson.toDomain() : null;
    }

    @Override
    public List<Lesson> fetchListByTeacherId(@NotNull UUID id){

        List<LessonModel> lessons = this.lessonDbRepository.findAll(
                Example.of(
                        LessonModel.builder().teacher(id)
                                .build()
                )
            );
        return lessons.stream().map(LessonModel::toDomain).collect(Collectors.toList());
    }
}
