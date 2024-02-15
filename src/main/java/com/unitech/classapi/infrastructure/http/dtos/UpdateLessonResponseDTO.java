package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.domain.entity.Lesson;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class UpdateLessonResponseDTO {
    private UUID id;
    private String description;
    private String title;
    private UUID teacher;
    private Date date;

    public static UpdateLessonResponseDTO toDTO(Lesson lesson){
        return UpdateLessonResponseDTO.builder()
                .id(lesson.getId())
                .description(lesson.getDescription())
                .title(lesson.getTitle())
                .teacher(lesson.getTeacher())
                .date(lesson.getDate())
                .build();
    }
}
