package com.unitech.classapi.application.usecase;

import com.unitech.classapi.TestDataBuilder;
import com.unitech.classapi.application.exceptions.NewUserRegistrationNotFoundedException;
import com.unitech.classapi.application.exceptions.UserAlreadyApprovedException;
import com.unitech.classapi.application.factory.*;
import com.unitech.classapi.application.port.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.domain.enums.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class ApproveUserTests {

    @Mock
    private UserPort userPort;

    @Mock
    private PendingUserPort pendingUserPort;

    @InjectMocks
    private ApproveUser approveUser;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should approve and create a user when a pending user its approved")
    void shouldCreateANewUserWhenAPendingUserItsApproved(){
        UUID id = UUID.randomUUID();
        PendingUser pendingUser = TestDataBuilder.generateRegistrationForTeacher();
        pendingUser.setId(id);
        User user = TestDataBuilder.generateNewUserByRegistration(pendingUser);



        when(pendingUserPort.fetchPendingUserById(id)).thenReturn(pendingUser);


        when(pendingUserPort.save(pendingUser)).thenReturn(pendingUser);
        when(userPort.save(id)).thenReturn(user);

        User result = approveUser.execute(id);

        assertEquals(Status.ACCEPTED, pendingUser.getStatus());

        verify(userPort, times(1)).save(id);

        assertEquals(user, result);
    }

    @Test
    @DisplayName("Should throw an error when the user registration is not found")
    void shouldThrowAnErrorWhenTheUserRegistrationIsNotFound(){
        UUID id = UUID.randomUUID();
        when(pendingUserPort.fetchPendingUserById(id)).thenReturn(null);

        assertThrows(NewUserRegistrationNotFoundedException.class, () -> approveUser.execute(id));

        verify(userPort, never()).save(any(UUID.class));
    }

    @Test
    public void testExecute_UserApproval_UserAlreadyApproved() {
        UUID id = UUID.randomUUID();
        PendingUser pendingUser = TestDataBuilder.generateRegistrationForTeacher();
        pendingUser.setStatus(Status.ACCEPTED);

        when(pendingUserPort.fetchPendingUserById(id)).thenReturn(pendingUser);

        assertThrows(UserAlreadyApprovedException.class, () -> approveUser.execute(id));

        verify(userPort, never()).save(any(UUID.class));
    }

}
