package com.edutech.backend.quiz.controller;

import com.edutech.backend.quiz.entity.Quiz;
import com.edutech.backend.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/quizzes")
@RequiredArgsConstructor
public class StudentQuizController {

    private final QuizService quizService;

    @GetMapping
    public List<Quiz> getQuizzesForStudent(Authentication authentication) {

        // String email = authentication.getName();

        // return quizService.getQuizzesForStudent(email);

        Long userId = Long.parseLong(authentication.getName());
        return quizService.getQuizzesForStudent(userId);

    }
    //to get quiz by id for student

    @GetMapping("/{quizId}")
public Quiz getQuizForStudent(
        @PathVariable Long quizId,
        Authentication authentication
) {

    Long userId = Long.parseLong(authentication.getName());

    return quizService.getQuizForStudent(userId, quizId);
}

//quiz evaluation

@PostMapping("/{quizId}/attempt")
public Object attemptQuiz(
        @PathVariable Long quizId,
        @RequestBody com.edutech.backend.quiz.dto.AttemptQuizRequest request,
        Authentication authentication
) {

    Long userId = Long.parseLong(authentication.getName());

    boolean isCorrect = quizService.attemptQuiz(
            userId,
            quizId,
            request.getSelectedAnswer()
    );

    return java.util.Map.of(
            "correct", isCorrect
    );
}

}
