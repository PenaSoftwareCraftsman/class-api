package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.port.LessonPort;
import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.Lesson;
import com.unitech.classapi.domain.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateLesson {

    private final UserPort userPort;

    private final LessonPort lessonPort;
    public Lesson execute(Lesson lesson){
        Teacher teacher = fetchTeacher(lesson.getTeacher());

        if(teacher == null) throw new RuntimeException(String.format("Teacher with %s id not founded", lesson.getTeacher()));

        return this.lessonPort.save(lesson);
    }

    private Teacher fetchTeacher(UUID id){
        return (Teacher) this.userPort.findUserById(id);

    }
}
