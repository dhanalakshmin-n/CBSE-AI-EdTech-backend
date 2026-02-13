package com.edutech.backend.syllabus.controller;

import com.edutech.backend.syllabus.dto.CreateSubjectRequest;
import com.edutech.backend.syllabus.entity.Subject;
import com.edutech.backend.syllabus.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public Subject addSubject(@RequestBody CreateSubjectRequest request) {
        return subjectService.addSubject(request);
    }

    //get subject by class
    @GetMapping("/{classLevel}")
public List<Subject> getSubjectsByClass(@PathVariable Integer classLevel) {
    return subjectService.getSubjectsByClass(classLevel);
}

   

}
