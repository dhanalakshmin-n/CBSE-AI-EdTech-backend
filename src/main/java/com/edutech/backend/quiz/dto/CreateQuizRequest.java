package com.edutech.backend.quiz.dto;

import lombok.Data;

@Data
public class CreateQuizRequest {

    private Integer classLevel;

    private String questionText;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String correctAnswer;
}
