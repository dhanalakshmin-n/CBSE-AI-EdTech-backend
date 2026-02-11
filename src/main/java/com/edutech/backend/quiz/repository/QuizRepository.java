package com.edutech.backend.quiz.repository;

import com.edutech.backend.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByClassLevel(Integer classLevel);

    
List<Quiz> findByQuestionTextContainingIgnoreCase(String keyword);


}


