package com.example.studentmanagement.dto;

import lombok.Data;

@Data
public class StudentResponse {
    private Long id;
    private String name;
    private int age;
    private String grade;
    private String address;
}