package com.unitech.classapi.infrastructure.http.dtos;

import com.unitech.classapi.domain.entity.Lesson;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.UUID;
@Data
@Builder
public class UpdateLessonDTO {

    private UUID id;
    @Nullable
    private String title;
    @Nullable
    private String description;
    @Nullable
    private Date date;
    @Nullable
    private UUID teacher;

    public Lesson toDomain(){
        return Lesson.builder()
                .id(id)
                .title(this.title)
                .description(this.description)
                .date(this.date)
                .teacher(this.teacher)
                .build();
    }
}
