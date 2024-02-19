package com.unitech.classapi.application.port;

import com.unitech.classapi.domain.entity.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserPort {

    void save(User user);

    User findUserById(UUID id);

    User fetchByEmail(String email);

}
