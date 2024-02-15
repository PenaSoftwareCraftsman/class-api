package com.unitech.classapi.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@Builder
public class Lesson {
    private UUID id;
    private String description;
    private String title;
    private UUID teacher;
    private Date date;


}
