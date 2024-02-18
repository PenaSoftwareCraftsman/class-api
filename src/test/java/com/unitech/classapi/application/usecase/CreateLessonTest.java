package com.unitech.classapi.application.usecase;

import com.unitech.classapi.TestDataBuilder;
import com.unitech.classapi.application.port.LessonPort;
import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.Lesson;
import com.unitech.classapi.domain.entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateLessonTest {

    @Mock
    private UserPort userPort;

    @Mock
    private LessonPort lessonPort;

    @InjectMocks
    private CreateLesson createLesson;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("should")
    public void shouldCreateANewLesson() {
        Lesson lesson = TestDataBuilder.generateLesson();

        Teacher teacher = TestDataBuilder.generateTeacher();
        lesson.setTeacher(teacher.getId());


        when(userPort.findUserById(any(UUID.class))).thenReturn(teacher);
        when(lessonPort.save(any(Lesson.class))).thenReturn(lesson);

        Lesson result = createLesson.execute(lesson);

        verify(lessonPort).save(lesson);
        assertEquals(lesson, result);

    }

    @Test
    @DisplayName("Should Throw An Exception For Teacher Not Founded")
    public void ShouldThrowAnExceptionForTeacherNotFounded() {

        Lesson lesson = TestDataBuilder.generateLesson();
        lesson.setTeacher(UUID.randomUUID());

        when(userPort.findUserById(any(UUID.class))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> createLesson.execute(lesson));
    }
}
