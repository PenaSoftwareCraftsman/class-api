package com.unitech.classapi.infrastructure.db.mongodb.adapter;

import com.unitech.classapi.application.exceptions.*;
import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.infrastructure.db.mongodb.model.*;
import com.unitech.classapi.infrastructure.db.mongodb.repository.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class UserDBAdapter implements UserPort {

    @Autowired
    private UserPendingDbRepository pendingUser;

    @Autowired
    private UserDbRepository user;

    private final Logger logger = LoggerFactory.getLogger(UserDBAdapter.class);


    @Override
    public User findUserById(UUID id){
        UserModel user = this.user.findOne(
                Example.of(UserModel
                        .toModel(Teacher.builder()
                                .id(id)
                                .build()
                        )
                )
                )
                .orElse(null);

        return user != null ? user.toDomain() : null;
    }

    @Override
    public User fetchByEmail(String email){
        UserModel userModel = this.user.findOne(Example.of(
                        UserModel.builder().email(email).build()
                )
        ).orElse(null);

        return userModel != null ? userModel.toDomain() : null;
    }

    @Override
    public void save(User user){
        logger.info("Creating new user ");
        this.user.save(UserModel.toModel(user));

    }
}
