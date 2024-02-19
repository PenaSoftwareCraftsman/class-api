package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.port.PendingUserPort;
import com.unitech.classapi.domain.entity.PendingUser;
import com.unitech.classapi.util.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ListDeniedUsersTests {

    @Mock
    private PendingUserPort pendingUserPort;

    @InjectMocks
    private ListDeniedUsers listDeniedUsers;


    @Test
    @DisplayName("Should list denied users")
    void shouldListDeniedUsers() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        List<PendingUser> deniedUsers = TestDataBuilder.generateDeniedUserList();
        when(pendingUserPort.fetchListOfDeniedUsers()).thenReturn(deniedUsers);

        // Act
        List<PendingUser> result = listDeniedUsers.execute();

        // Assert
        assertEquals(deniedUsers.size(), result.size());
        result.forEach(actual -> {
                  var expected = deniedUsers.stream().filter(user -> user.getId().equals(actual.getId())).findFirst().orElseThrow(
                          () -> new RuntimeException("User not found")
                  );
                  assertAll(
                          () -> assertEquals(expected.getId(), actual.getId()),
                          () -> assertEquals(expected.getName(), actual.getName()),
                          () -> assertEquals(expected.getEmail(), actual.getEmail()),
                          () -> assertEquals(expected.getPassword(), actual.getPassword()),
                          () -> assertEquals(expected.getRole(), actual.getRole()),
                          () -> assertEquals(expected.getStatus(), actual.getStatus())
                  );
                }
        );
    }
}
