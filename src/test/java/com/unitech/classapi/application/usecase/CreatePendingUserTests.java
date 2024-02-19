package com.unitech.classapi.application.usecase;

import com.unitech.classapi.util.TestDataBuilder;
import com.unitech.classapi.application.port.*;
import com.unitech.classapi.domain.entity.*;
import org.junit.jupiter.api.*;
import org.mockito.*;


import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
        PendingUser pendingUser = TestDataBuilder.generateRegistrationForTeacher();


        when(pendingUserPort.fetchByEmail(pendingUser.getEmail())).thenReturn(null);
        doNothing().when(pendingUserPort).save(any(PendingUser.class));

        createPendingUser.execute(pendingUser);

        verify(pendingUserPort, times(1)).fetchByEmail(pendingUser.getEmail());
    }

    @Test
    void shouldThrowAnExceptionWhenUserExist() {
        PendingUser pendingUser = TestDataBuilder.generateRegistrationForTeacher();

        when(pendingUserPort.fetchByEmail(pendingUser.getEmail())).thenReturn(pendingUser);
        assertThrows(RuntimeException.class, () -> {
            createPendingUser.execute(pendingUser);
        });

        verify(pendingUserPort, times(1)).fetchByEmail(pendingUser.getEmail());

        verify(pendingUserPort, never()).save(pendingUser);
    }

}
