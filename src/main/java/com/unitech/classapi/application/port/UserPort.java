package com.unitech.classapi.application.port;

import com.unitech.classapi.domain.entity.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserPort {

    User save(UUID id);

    User findUserById(UUID id);

}
