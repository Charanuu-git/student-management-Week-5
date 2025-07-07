package com.example.studentmanagement.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class StudentRequest {
    @NotBlank(message = "Name must not be empty")
    private String name;

    @Positive(message = "Age must be positive")
    private int age;

    @Pattern(regexp = "^(A\\+|A|A-|B\\+|B|B-|C\\+|C|C-)$", message = "Grade must be like A+, B, C-")
    private String grade;

    private String address;
}