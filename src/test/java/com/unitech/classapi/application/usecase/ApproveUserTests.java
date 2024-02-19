package com.unitech.classapi.application.usecase;

import com.unitech.classapi.*;
import com.unitech.classapi.application.exceptions.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import com.unitech.classapi.application.port.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApproveUserTests {

    @Mock
    private UserPort userPort;

    @Mock
    private PendingUserPort pendingUserPort;

    @InjectMocks
    private ApproveUser approveUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should approve a pending user by id")
    void shouldApprovePendingUserById() {
        PendingUser pendingUser = TestDataBuilder.generateRegistrationForTeacher();

        when(pendingUserPort.fetchPendingUserById(pendingUser.getId())).thenReturn(pendingUser);
        doNothing().when(pendingUserPort).approve(pendingUser.getId());

        approveUser.execute(pendingUser.getId());

        verify(pendingUserPort, times(1)).approve(pendingUser.getId());
        verify(userPort, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should throw an exception when user registration is not found")
    void shouldThrowExceptionWhenUserRegistrationNotFound() {
        UUID id = UUID.randomUUID();
        when(pendingUserPort.fetchPendingUserById(id)).thenThrow(new NewUserRegistrationNotFoundedException("User registration not found"));

        assertThrows(NewUserRegistrationNotFoundedException.class, () -> approveUser.execute(id));
        verify(pendingUserPort, times(1)).fetchPendingUserById(id);
        verifyNoInteractions(userPort);
    }

    @Test
    @DisplayName("Should throw an exception when user is already approved")
    void shouldThrowExceptionWhenUserAlreadyApproved() {
        PendingUser pendingUser = TestDataBuilder.generateRegistrationForTeacher();
        pendingUser.setStatus(UserStatus.APPROVED);
        UUID id = pendingUser.getId();
        when(pendingUserPort.fetchPendingUserById(id)).thenReturn(pendingUser);

        assertThrows(UserAlreadyApprovedException.class, () -> approveUser.execute(id));
        verify(pendingUserPort, times(1)).fetchPendingUserById(id);
        verifyNoInteractions(userPort);
    }
}
