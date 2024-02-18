package com.unitech.classapi;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.Role;
import com.unitech.classapi.domain.enums.UserStatus;

import java.util.Date;
import java.util.UUID;

public class TestDataBuilder {

    public static Teacher generateTeacher(){
        return Teacher.builder()
                .id(UUID.randomUUID())
                .name("Test Teacher")
                .email("test@gmail.com")
                .password(Password.create("12345678").toString())
                .build();
    }



    public static Lesson generateLesson(){
        return Lesson.builder()
                .id(UUID.randomUUID())
                .description("For studants")
                .title("Matematics")
                .date(new Date())
                .build();
    }

    public static PendingUser generateRegistrationForTeacher(){
        return PendingUser.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .password(Password.create("password123"))
                .email("john.doe@example.com")
                .role(Role.TEACHER)
                .status(UserStatus.PENDING)
                .build();
    }

    public static  User generateNewUserByRegistration(PendingUser registration){
        return switch (registration.getRole()){
            case TEACHER -> Teacher.builder().id(registration.getId()).name(registration.getName()).password(registration.getPassword()).email(registration.getEmail()).build();
            case SECRETARY -> Secretary.builder().id(registration.getId()).name(registration.getName()).password(registration.getPassword()).email(registration.getEmail()).build();
        };
    }
}
