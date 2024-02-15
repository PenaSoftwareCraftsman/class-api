package com.unitech.classapi.infrastructure.http.controller.lesson;

import com.unitech.classapi.application.usecase.CreateLesson;
import com.unitech.classapi.domain.entity.Lesson;
import com.unitech.classapi.infrastructure.http.dtos.CreateLessonRequestDTO;
import com.unitech.classapi.infrastructure.http.dtos.CreateLessonResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    private CreateLesson createLesson;

    @PostMapping("/create")
    public ResponseEntity<CreateLessonResponseDTO> createLesson(
            @RequestBody CreateLessonRequestDTO createLessonDTO
    ) {
        Lesson lesson = createLesson.execute(createLessonDTO.toDomain());
        return ResponseEntity.ok(CreateLessonResponseDTO.toDTO(lesson));
    }
}
