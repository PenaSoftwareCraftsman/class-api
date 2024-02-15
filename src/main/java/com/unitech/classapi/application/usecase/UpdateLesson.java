package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.exceptions.LessonNotFoundException;
import com.unitech.classapi.application.port.LessonPort;
import com.unitech.classapi.domain.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateLesson {

    private final LessonPort lessonPort;

    public Lesson execute(UUID id, Lesson newLesson){
        Lesson actualLesson = this.lessonPort.fetchById(id);

        if(actualLesson == null) throw new LessonNotFoundException(newLesson.getId().toString());
        compareId(id, newLesson.getId());
        Lesson updated = mergeLessons(actualLesson, newLesson);

        return this.lessonPort.save(updated);
    }

    private void compareId(UUID id, UUID documentId){
        if(!id.equals(documentId)) throw new RuntimeException("The provided ID does not match the document's ID.");
    }

    private Lesson mergeLessons(Lesson actualLesson, Lesson newLesson){

        if(newLesson.getTitle() != null) actualLesson.setTitle(newLesson.getTitle());
        if(newLesson.getDescription() != null) actualLesson.setDescription(newLesson.getDescription());
        if(newLesson.getTeacher() != null) actualLesson.setTeacher(newLesson.getTeacher());
        if(newLesson.getDate() != null) actualLesson.setDate(newLesson.getDate());

        return newLesson;
    }
}
