package com.edutech.backend.quiz.controller;

import com.edutech.backend.quiz.entity.Quiz;
import com.edutech.backend.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.edutech.backend.quiz.dto.CreateQuizRequest;
import com.edutech.backend.quiz.dto.UpdateQuizRequest;

import java.util.List;

@RestController
@RequestMapping("/api/admin/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    //get quiz by class
    
    @GetMapping
    public List<Quiz> getQuizzesByClass(
            @RequestParam Integer classLevel
    ) {
        return quizService.getQuizzesByClass(classLevel);
    }

    //post quiz
    @PostMapping
    public Quiz createQuiz(@RequestBody CreateQuizRequest request) {
        return quizService.createQuiz(request);
    }

    //delete quiz
    @DeleteMapping("/{id}")
public String deleteQuiz(@PathVariable Long id) {
    quizService.deleteQuiz(id);
    return "Quiz deleted successfully";
}

//update quiz

    @PutMapping("/{id}")
public Quiz updateQuiz(
        @PathVariable Long id,
        @RequestBody UpdateQuizRequest request
) {
    return quizService.updateQuiz(id, request);
}

//search quiz by keyword
@GetMapping("/search")
public List<Quiz> searchQuizzes(@RequestParam String keyword) {
    return quizService.searchQuizzes(keyword);
}



}
