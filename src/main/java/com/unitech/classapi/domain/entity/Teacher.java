package com.unitech.classapi.domain.entity;

import com.unitech.classapi.domain.enums.*;
import lombok.*;

import java.util.*;

@Getter
public class Teacher extends User{
    @Builder
    public Teacher(UUID id, String name, String password, String email){
        super(id, name, password, email, Role.TEACHER);
    }
}
