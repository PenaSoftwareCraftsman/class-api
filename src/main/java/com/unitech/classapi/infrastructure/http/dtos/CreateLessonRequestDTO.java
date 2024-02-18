package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.domain.entity.Lesson;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class CreateLessonRequestDTO {
    private String title;
    private String description;
    private Date date;

    public Lesson toDomain(UUID teacherId){
        return Lesson.builder()
                .id(UUID.randomUUID())
                .title(this.title)
                .description(this.description)
                .date(this.date)
                .teacher(teacherId)
                .build();
    }
}
