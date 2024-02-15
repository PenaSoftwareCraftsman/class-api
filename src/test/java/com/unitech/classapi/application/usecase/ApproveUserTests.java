package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.factory.*;
import com.unitech.classapi.application.port.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.mockito.MockitoAnnotations.openMocks;

public class ApproveUserTests {

    @Mock
    private UserPort userPort;

    @InjectMocks
    private ApproveTeacher approveUser;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should create a user when a pending user its approved")
    void shouldCreateANewUserWhenAPendingUserItsApproved(){
        PendingUser pendingUser = UserFactory.buildPendingUser(UUID.randomUUID(), "John Doe",  "johndoe@email.com","passwordhashed", Role.TEACHER, Status.PENDING);


    }

}
