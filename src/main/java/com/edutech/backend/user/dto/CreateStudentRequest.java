package com.edutech.backend.user.dto;

import lombok.Data;

@Data
public class CreateStudentRequest {
    private String name;
    private String email;
    private String password;
    private String studentClass;
    private String schoolName;
}
