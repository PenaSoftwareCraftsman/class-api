package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.factory.*;
import com.unitech.classapi.application.port.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import org.junit.jupiter.api.*;
import org.mockito.*;


import java.util.*;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


public class CreatePendingUserTests {
    @Mock
    private PendingUserPort pendingUserPort;

    @Mock
    private Logger logger;

    @InjectMocks
    private CreatePendingUser createPendingUser;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should create a new pending user")
    void shouldCreateNewPendingUserWithTeacherRole(){
        PendingUser pendingUser = UserFactory.buildPendingUser(UUID.randomUUID(), "John Doe",  "johndoe@email.com", "passwordhashed", Role.TEACHER, Status.PENDING);


        when(pendingUserPort.fetchByEmail(pendingUser.getEmail())).thenReturn(null);
        when(pendingUserPort.save(any(PendingUser.class))).thenReturn(pendingUser);

        PendingUser createdPendingUser = createPendingUser.execute(pendingUser);

        assertEquals(pendingUser, createdPendingUser);
    }

    @Test
    void shouldThrowAnExceptionWhenUserExist() {
        PendingUser pendingUser = UserFactory.buildPendingUser(UUID.randomUUID(), "John Doe",  "johndoe@email.com","passwordhashed", Role.TEACHER, Status.PENDING);

        when(pendingUserPort.fetchByEmail(pendingUser.getEmail())).thenReturn(pendingUser);
        assertThrows(RuntimeException.class, () -> {
            createPendingUser.execute(pendingUser);
        });

        verify(pendingUserPort, times(1)).fetchByEmail(pendingUser.getEmail());

        verify(pendingUserPort, never()).save(pendingUser);
    }

}
