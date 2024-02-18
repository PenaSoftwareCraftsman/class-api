package com.unitech.classapi.application.usecase;

import com.unitech.classapi.*;
import com.unitech.classapi.application.exceptions.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import com.unitech.classapi.infrastructure.db.mongodb.adapter.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class DenyUserTests {


    @Mock
    private PendingUserDBAdapter pendingUserPort;
    @InjectMocks
    private DenyUser denyUser;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }
    @Test
    @DisplayName("Should deny an pending user by id")
    void shouldDenyAnPendingUserById(){
        PendingUser pendingUser = TestDataBuilder.generateRegistrationForTeacher();
        when(pendingUserPort.fetchPendingUserById(pendingUser.getId())).thenReturn(pendingUser);
        doNothing().when(pendingUserPort).deny(pendingUser.getId());
        denyUser.execute(pendingUser.getId());
        verify(pendingUserPort, times(1)).deny(pendingUser.getId());
    }

    @Test
    @DisplayName("Should throw an exception when user registration are not found")
    void shouldThrowAnExceptionWhenUserRegistrationAreNotFound(){
        UUID id = UUID.randomUUID();
        when(pendingUserPort.fetchPendingUserById(id)).thenReturn(null);
        assertThrows(NewUserRegistrationNotFoundedException.class, () -> denyUser.execute(id));

    }

    @Test
    @DisplayName("Should throw an exception when an user its already approved")
    void shouldThrowAnExceptionWhenUserAlreadyApproved(){
        PendingUser pendingUser = TestDataBuilder.generateRegistrationForTeacher();
        pendingUser.setStatus(UserStatus.APPROVED);
        UUID id = pendingUser.getId();
        when(pendingUserPort.fetchPendingUserById(id)).thenReturn(pendingUser);
        assertThrows(UserAlreadyApprovedException.class, () -> denyUser.execute(id));
    }

    @Test
    @DisplayName("Should throw an exception when user its already denied")
    void shouldThrowAnExceptionWhenUserAlreadyDenied(){
        PendingUser pendingUser = TestDataBuilder.generateRegistrationForTeacher();
        pendingUser.setStatus(UserStatus.DENIED);
        UUID id = pendingUser.getId();
        when(pendingUserPort.fetchPendingUserById(id)).thenReturn(pendingUser);
        assertThrows(UserAlreadyDeniedException.class, () -> denyUser.execute(id));
    }
}
