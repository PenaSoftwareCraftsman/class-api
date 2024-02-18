package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.port.LessonPort;
import com.unitech.classapi.domain.entity.Lesson;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ListLessons {

    private final LessonPort lessonPort;

    public List<Lesson> execute(@NotNull UUID id){
        return lessonPort.fetchListByTeacherId(id);

    }

}
