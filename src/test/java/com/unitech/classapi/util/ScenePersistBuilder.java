package com.unitech.classapi.util;

import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.infrastructure.db.mongodb.model.*;
import com.unitech.classapi.infrastructure.db.mongodb.repository.*;
import com.unitech.classapi.infrastructure.security.util.*;
import lombok.*;
import org.springframework.data.mongodb.core.*;
import org.springframework.stereotype.*;

import java.util.*;

import static com.unitech.classapi.util.TestDataBuilder.generateDeniedUserModel;
import static com.unitech.classapi.util.TestDataBuilder.generatePendingTeacherUserModel;

@Service
@RequiredArgsConstructor
public class ScenePersistBuilder {
    private final MongoTemplate mongoTemplate;
    public final UserDbRepository userDbRepository;
    public final UserPendingDbRepository userPendingDbRepository;
    private final JwtUtil jwtUtil;

    public void resetDatabase(){
        mongoTemplate.getDb().drop();
    }
    public UserModel buildValidUser(){
        var newUser = TestDataBuilder.generateValidTeacherUserModel();
        return userDbRepository.save(newUser);
    }

    public PendingUserModel buildValidPendingTeacherUser(){
        var newUser = generatePendingTeacherUserModel();
        return userPendingDbRepository.save(newUser);
    }

    public UserModel buildValidSecretaryUser(){
        var newUser = TestDataBuilder.generateValidSecretaryUserModel();
        return userDbRepository.save(newUser);
    }


    public Token buildValidTokenByUser(UserModel user){
        return jwtUtil.generateToken(user.toDomain());
    }

    public List<PendingUserModel> buildListValidPendingTeacherUser(int quantity) {
        List<PendingUserModel> pendingUsers = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            pendingUsers.add(generatePendingTeacherUserModel());
        }
        return userPendingDbRepository.saveAll(pendingUsers);
    }

    public List<PendingUserModel> buildListValidDeniedTeacherUser(int quantity) {
        List<PendingUserModel> pendingUsers = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            pendingUsers.add(generateDeniedUserModel());
        }
        return userPendingDbRepository.saveAll(pendingUsers);
    }
}
