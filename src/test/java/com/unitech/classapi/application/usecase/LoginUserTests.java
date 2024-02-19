package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.exceptions.*;
import com.unitech.classapi.application.port.*;
import com.unitech.classapi.domain.entity.*;
import com.unitech.classapi.infrastructure.security.util.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static com.unitech.classapi.util.TestDataBuilder.default_password;
import static com.unitech.classapi.util.TestDataBuilder.generateValidToken;
import static com.unitech.classapi.util.TestDataBuilder.generateValidaUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginUserTests {

    @Mock
    private UserPort userPort;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private LoginUser loginUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should login user successfully and generate token")
    void shouldLoginUserSuccessfully() {
        // Arrange
        User user = generateValidaUser();
        String expectedToken = "generatedToken";

        // Mock behavior
        when(userPort.fetchByEmail(user.getEmail())).thenReturn(user);
        when(jwtUtil.generateToken(user)).thenReturn(
                generateValidToken(expectedToken)
        );

        // Act
        Token result = loginUser.execute(user.getEmail(), default_password);

        // Assert
        assertEquals(expectedToken, result.getValue());
        verify(userPort, times(1)).fetchByEmail(user.getEmail());
        verify(jwtUtil, times(1)).generateToken(user);
    }

    @Test
    @DisplayName("Should throw InvalidEmailOrPasswordException when email or password is invalid")
    void shouldThrowExceptionForInvalidEmailOrPassword() {
        // Arrange
        String email = "test@example.com";
        String password = "password";

        // Mock behavior
        when(userPort.fetchByEmail(email)).thenReturn(null); // Simulate user not found

        // Act and Assert
        assertThrows(InvalidEmailOrPasswordException.class, () -> loginUser.execute(email, password));
        verify(userPort, times(1)).fetchByEmail(email);
    }
}