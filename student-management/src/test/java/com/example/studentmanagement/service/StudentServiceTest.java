package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStudent() {
        Student student = new Student(null, "John Doe", 20, "A+", "123 Main St");
        Student savedStudent = new Student(1L, "John Doe", 20, "A+", "123 Main St");

        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

        Student result = studentService.addStudent(student);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentById() {
        Student student = new Student(1L, "Jane Doe", 22, "B", "456 Elm St");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Jane Doe", result.getName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateStudent() {
        Student existing = new Student(1L, "Old Name", 21, "C+", "789 Oak St");
        Student updated = new Student(1L, "New Name", 22, "A", "789 Oak St");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(studentRepository.save(any(Student.class))).thenReturn(updated);

        Student result = studentService.updateStudent(1L, updated);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).save(existing);
    }

    @Test
    void testDeleteStudent() {
        Long studentId = 1L;
        doNothing().when(studentRepository).deleteById(studentId);

        studentService.deleteStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }
}