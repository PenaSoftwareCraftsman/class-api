package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.domain.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class LessonDTO {
    private String title;
    private String description;
    private Date date;
    private UUID teacher;

    public static LessonDTO toDTO(Lesson lesson){
        return LessonDTO.builder()
                .title(lesson.getTitle())
                .description(lesson.getDescription())
                .date(lesson.getDate())
                .teacher(lesson.getTeacher())
                .build();
    }

    public static List<LessonDTO> toDTO(List<Lesson> lessons){
        return lessons.stream().map(LessonDTO::toDTO).toList();
    }
}
