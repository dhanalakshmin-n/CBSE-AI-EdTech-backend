package com.edutech.backend.syllabus.dto;

import lombok.Data;

@Data
public class UploadMaterialRequest {
    private Integer classLevel;
    private Long subjectId;
}
