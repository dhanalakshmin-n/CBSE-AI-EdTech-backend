package com.edutech.backend.syllabus.repository;

import com.edutech.backend.syllabus.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findByName(String name);
    Optional<Subject> findByClassLevelAndName(Integer classLevel, String name);

}
