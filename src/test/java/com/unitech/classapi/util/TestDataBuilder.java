package com.unitech.classapi.util;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.UserRole;
import com.unitech.classapi.domain.enums.UserStatus;
import com.unitech.classapi.infrastructure.db.mongodb.model.*;
import com.unitech.classapi.infrastructure.http.dtos.*;

import java.util.*;

public class TestDataBuilder {

    public static final String default_password = "12345678";
    public static Teacher generateTeacher(){
        return Teacher.builder()
                .id(UUID.randomUUID())
                .name("Test Teacher")
                .email("test@gmail.com")
                .password(Password.create(default_password).toString())
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
                .password(Password.create(default_password))
                .email("john.doe@example.com")
                .role(UserRole.TEACHER)
                .status(UserStatus.PENDING)
                .build();
    }

    public static PendingUserModel generatePendingTeacherUserModel(){
        return PendingUserModel.builder()
                .id(UUID.randomUUID())
                .name("teacher Doe")
                .password_hash(Password.create(default_password).getContent())
                .email("teacher.doe@example.com")
                .role(UserRole.TEACHER.name())
                .status(UserStatus.PENDING)
                .build();
    }

    public static  PendingUserModel generateDeniedUserModel(){
        var  validDeniedUser = generatePendingTeacherUserModel();
        validDeniedUser.setStatus(UserStatus.DENIED);
        return validDeniedUser;
    }

    public static Token generateValidToken(String expectedToken) {
        return Token.builder().value(expectedToken).build();
    }

    public static User generateValidaUser(){
        return Teacher.builder()
                .id(UUID.randomUUID())
                .name("Valid User")
                .password(Password.create(default_password).getContent())
                .email("valid.doe@example.com")
                .build();
    }

    public static NewPendingUserRequestDTO generateNewPendingUserRequestDTO(){
        return NewPendingUserRequestDTO.builder()
                .name("John Doe")
                .password(Password.create(default_password).getContent())
                .email("john.doe@example.com")
                .role(UserRole.TEACHER)
                .build();
    }
    public static  User generateNewUserByRegistration(PendingUser registration){
        return switch (registration.getRole()){
            case TEACHER -> Teacher.builder().id(registration.getId()).name(registration.getName()).password(registration.getPassword()).email(registration.getEmail()).build();
            case SECRETARY -> Secretary.builder().id(registration.getId()).name(registration.getName()).password(registration.getPassword()).email(registration.getEmail()).build();
        };
    }

    public static List<PendingUser> generateDeniedUserList() {
        var password = Password.create(default_password);

        List<PendingUser> deniedUsers = new ArrayList<>();
        deniedUsers.add(
                PendingUser.builder()
                        .id(UUID.randomUUID())
                        .name("User1")
                        .email("user1@example.com")
                        .password(password)
                        .role(UserRole.TEACHER)
                        .status(UserStatus.DENIED)
                        .build()
        );
        deniedUsers.add(
                PendingUser.builder()
                        .id(UUID.randomUUID())
                        .name("User2")
                        .email("user2@example.com")
                        .password(password)
                        .role(UserRole.TEACHER)
                        .status(UserStatus.DENIED)
                        .build()
        );
        deniedUsers.add(
                PendingUser.builder()
                        .id(UUID.randomUUID())
                        .name("User3")
                        .email("user3@example.com")
                        .password(password)
                        .role(UserRole.SECRETARY)
                        .status(UserStatus.DENIED)
                        .build()
        );
        return deniedUsers;
    }

    public static UserModel generateValidTeacherUserModel() {
        return UserModel.builder()
                .id(UUID.randomUUID())
                .name("Teacher Doe")
                .password(Password.create(default_password).getContent())
                .email("teacher.doe@example.com")
                .role(UserRole.TEACHER.toString())
                .build();
    }

    public static UserModel generateValidSecretaryUserModel(){
        return UserModel.builder()
                .id(UUID.randomUUID())
                .name("Secretary Doe")
                .password(Password.create(default_password).getContent())
                .email("secret.doe@example.com")
                .role(UserRole.SECRETARY.toString())
                .build();
    }

    public static  String generateValidToken(){
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjbGFzcy1hcGkiLCJzdWIiOiJzZWNyZXQuZG9lQGV4YW1wbGUuY29tIiwiaWQiOiI4MDM2OTI5MS1iZmQ3LTQ0MWItYjllNy1mM2UzZmQwZDhhOWIiLCJub21lIjoiU2VjcmV0YXJ5IERvZSIsImVtYWlsIjoic2VjcmV0LmRvZUBleGFtcGxlLmNvbSIsInJvbGUiOiJTRUNSRVRBUlkiLCJleHAiOjE3MDgzMjczODh9.tn4XnfBsNlp4psMbfGK61XTo4H9q0EOHcD6qaxfGqNE";
    }
}
