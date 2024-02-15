package com.unitech.classapi.infrastructure.db.mongodb.model;

import com.unitech.classapi.domain.entity.Lesson;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
@Data
@EqualsAndHashCode(of = "id")
@Builder
@Document(collection = "lesson")
public class LessonModel {
    @Id
    private UUID id;
    private String description;
    private String title;
    private UUID teacher;
    private Date date;

    public static LessonModel toModel(Lesson lesson){
        return LessonModel.builder()
                .id(lesson.getId())
                .description(lesson.getDescription())
                .title(lesson.getTitle())
                .teacher(lesson.getTeacher())
                .date(lesson.getDate())
                .build();
    }

    public Lesson toDomain(){
        return Lesson.builder()
                .id(this.id)
                .description(this.description)
                .title(this.title)
                .teacher(this.teacher)
                .date(this.date)
                .build();
    }
}
