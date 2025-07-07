package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody StudentRequest studentRequest) {
        Student student = studentService.addStudent(mapToStudent(studentRequest));
        return ResponseEntity.ok(mapToStudentResponse(student));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents()
                .stream()
                .map(this::mapToStudentResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(mapToStudentResponse(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest studentRequest) {
        Student updated = studentService.updateStudent(id, mapToStudent(studentRequest));
        return ResponseEntity.ok(mapToStudentResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // Helper methods for mapping
    private Student mapToStudent(StudentRequest dto) {
        return Student.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .grade(dto.getGrade())
                .address(dto.getAddress())
                .build();
    }

    private StudentResponse mapToStudentResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setAge(student.getAge());
        response.setGrade(student.getGrade());
        response.setAddress(student.getAddress());
        return response;
    }
}