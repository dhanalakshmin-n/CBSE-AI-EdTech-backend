package com.edutech.backend.syllabus.controller;

import com.edutech.backend.syllabus.dto.CreateSubjectRequest;
import com.edutech.backend.syllabus.entity.Subject;
import com.edutech.backend.syllabus.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public Subject addSubject(@RequestBody CreateSubjectRequest request) {
        return subjectService.addSubject(request);
    }
}
