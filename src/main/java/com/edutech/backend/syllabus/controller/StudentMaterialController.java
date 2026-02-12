package com.edutech.backend.syllabus.controller;

import com.edutech.backend.syllabus.entity.Material;
import com.edutech.backend.syllabus.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/materials")
@RequiredArgsConstructor
public class StudentMaterialController {

    private final MaterialService materialService;

    @GetMapping
public List<Material> getMaterialsForStudent(
        @RequestParam(required = false) Long subjectId,
        Authentication authentication
) {

    Long userId = Long.parseLong(authentication.getName());

    return materialService.getMaterialsForStudent(userId, subjectId);
}
//download material for student

@GetMapping("/{materialId}/download")
public org.springframework.http.ResponseEntity<org.springframework.core.io.Resource> downloadMaterial(
        @PathVariable Long materialId,
        Authentication authentication
) throws Exception {

    Long userId = Long.parseLong(authentication.getName());

    Material material = materialService.getMaterialForStudent(userId, materialId);

    java.nio.file.Path path = java.nio.file.Paths.get(material.getFilePath());

    org.springframework.core.io.Resource resource =
            new org.springframework.core.io.UrlResource(path.toUri());

    return org.springframework.http.ResponseEntity.ok()
            .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + material.getFileName() + "\"")
            .body(resource);
}


}
