package com.edutech.backend.syllabus.service;

import com.edutech.backend.syllabus.dto.CreateSubjectRequest;
import com.edutech.backend.syllabus.entity.Subject;
import com.edutech.backend.syllabus.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public Subject addSubject(CreateSubjectRequest request) {

    subjectRepository
        .findByClassLevelAndName(request.getClassLevel(), request.getName())
        .ifPresent(s -> {
            throw new RuntimeException("Subject already exists for this class");
        });

    Subject subject = Subject.builder()
            .classLevel(request.getClassLevel())
            .name(request.getName())
            .build();

    return subjectRepository.save(subject);
}
}
