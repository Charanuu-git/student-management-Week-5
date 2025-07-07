package com.example.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @Positive(message = "Age must be positive")
    private int age;

    @Pattern(regexp = "^(A\\+|A|A-|B\\+|B|B-|C\\+|C|C-)$", message = "Grade must be like A+, B, C-")
    private String grade;

    private String address;
}