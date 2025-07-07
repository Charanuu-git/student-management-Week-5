package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStudent() {
        StudentRequest request = new StudentRequest();
        request.setName("John Doe");
        request.setAge(20);
        request.setGrade("A+");
        request.setAddress("123 Main St");

        Student student = Student.builder()
                .id(1L)
                .name("John Doe")
                .age(20)
                .grade("A+")
                .address("123 Main St")
                .build();

        when(studentService.addStudent(any(Student.class))).thenReturn(student);

        ResponseEntity<StudentResponse> response = studentController.addStudent(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getName());
        verify(studentService, times(1)).addStudent(any(Student.class));
    }

    @Test
    void testGetAllStudents() {
        Student student1 = Student.builder().id(1L).name("John Doe").age(20).grade("A+").address("123 Main St").build();
        Student student2 = Student.builder().id(2L).name("Jane Doe").age(21).grade("B").address("456 Elm St").build();

        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student1, student2));

        ResponseEntity<List<StudentResponse>> response = studentController.getAllStudents();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetStudentById() {
        Student student = Student.builder().id(1L).name("John Doe").age(20).grade("A+").address("123 Main St").build();

        when(studentService.getStudentById(1L)).thenReturn(student);

        ResponseEntity<StudentResponse> response = studentController.getStudentById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getName());
        verify(studentService, times(1)).getStudentById(1L);
    }

    @Test
    void testUpdateStudent() {
        StudentRequest request = new StudentRequest();
        request.setName("Updated Name");
        request.setAge(22);
        request.setGrade("B+");
        request.setAddress("789 Oak St");

        Student updatedStudent = Student.builder()
                .id(1L)
                .name("Updated Name")
                .age(22)
                .grade("B+")
                .address("789 Oak St")
                .build();

        when(studentService.updateStudent(eq(1L), any(Student.class))).thenReturn(updatedStudent);

        ResponseEntity<StudentResponse> response = studentController.updateStudent(1L, request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Name", response.getBody().getName());
        verify(studentService, times(1)).updateStudent(eq(1L), any(Student.class));
    }

    @Test
    void testDeleteStudent() {
        doNothing().when(studentService).deleteStudent(1L);

        ResponseEntity<Void> response = studentController.deleteStudent(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(studentService, times(1)).deleteStudent(1L);
    }
}