package com.edutech.backend.syllabus.dto;

import lombok.Data;

@Data
public class CreateSubjectRequest {
    private Integer classLevel; // 8–12
    private String name;        
}
