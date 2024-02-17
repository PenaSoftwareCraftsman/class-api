package com.unitech.classapi.infrastructure.http.controller.lesson;

import com.unitech.classapi.application.usecase.CreateLesson;
import com.unitech.classapi.application.usecase.ListLessons;
import com.unitech.classapi.application.usecase.UpdateLesson;
import com.unitech.classapi.domain.entity.Lesson;
import com.unitech.classapi.infrastructure.http.dtos.CreateLessonRequestDTO;
import com.unitech.classapi.infrastructure.http.dtos.CreateLessonResponseDTO;
import com.unitech.classapi.infrastructure.http.dtos.UpdateLessonDTO;
import com.unitech.classapi.infrastructure.http.dtos.UpdateLessonResponseDTO;
import com.unitech.classapi.infrastructure.http.dtos.LessonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    private CreateLesson createLesson;
    @Autowired
    private UpdateLesson updateLesson;
    @Autowired
    private ListLessons listLessons;

    @PostMapping("/create")
    public ResponseEntity<CreateLessonResponseDTO> createLesson(
            @RequestBody CreateLessonRequestDTO createLessonDTO
    ) {
        Lesson lesson = createLesson.execute(createLessonDTO.toDomain());
        return ResponseEntity.ok(CreateLessonResponseDTO.toDTO(lesson));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateLessonResponseDTO> updateLesson(
            @PathVariable UUID id,
            @RequestBody UpdateLessonDTO lessonDTO
    ){
        Lesson lesson = updateLesson.execute(id, lessonDTO.toDomain());
        return ResponseEntity.ok(UpdateLessonResponseDTO.toDTO(lesson));
    }

    @GetMapping()
    public ResponseEntity<List<LessonDTO>> fetchLessonsByTeacher(
            @AuthenticationPrincipal(expression = "id") UUID id
    ){

        List<Lesson> lessons = listLessons.execute(id);

        return ResponseEntity.ok(LessonDTO.toDTO(lessons));
    }
}
