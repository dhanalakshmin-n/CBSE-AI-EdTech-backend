package com.edutech.backend.quiz.service;

import com.edutech.backend.quiz.dto.CreateQuizRequest;
import com.edutech.backend.quiz.dto.UpdateQuizRequest;
import com.edutech.backend.quiz.entity.Quiz;
import com.edutech.backend.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public List<Quiz> getQuizzesByClass(Integer classLevel) {
        return quizRepository.findByClassLevel(classLevel);
    }

//create quiz
public Quiz createQuiz(CreateQuizRequest request) {

    Quiz quiz = Quiz.builder()
            .classLevel(request.getClassLevel())
            .questionText(request.getQuestionText())
            .option1(request.getOption1())
            .option2(request.getOption2())
            .option3(request.getOption3())
            .option4(request.getOption4())
            .correctAnswer(request.getCorrectAnswer())
            .createdAt(java.time.LocalDateTime.now())
            .build();

    return quizRepository.save(quiz);
}
//delete quiz
public void deleteQuiz(Long id) {
    Quiz quiz = quizRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Quiz not found"));

    quizRepository.delete(quiz);
}

//update quiz
public Quiz updateQuiz(Long id, UpdateQuizRequest request) {

    Quiz existingQuiz = quizRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Quiz not found"));

    existingQuiz.setClassLevel(request.getClassLevel());
    existingQuiz.setQuestionText(request.getQuestionText());
    existingQuiz.setOption1(request.getOption1());
    existingQuiz.setOption2(request.getOption2());
    existingQuiz.setOption3(request.getOption3());
    existingQuiz.setOption4(request.getOption4());
    existingQuiz.setCorrectAnswer(request.getCorrectAnswer());

    return quizRepository.save(existingQuiz);
}

//search quiz using keywords

public List<Quiz> searchQuizzes(String keyword) {
    return quizRepository.findByQuestionTextContainingIgnoreCase(keyword);
}


}
