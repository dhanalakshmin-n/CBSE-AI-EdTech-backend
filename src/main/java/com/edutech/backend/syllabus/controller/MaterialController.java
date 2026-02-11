package com.edutech.backend.syllabus.controller;

import com.edutech.backend.syllabus.entity.Material;
import com.edutech.backend.syllabus.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/api/admin/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

   @PostMapping(
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
public Material uploadMaterial(
        @RequestParam Integer classLevel,
        @RequestParam Long subjectId,
        @RequestParam String displayName,
        @RequestParam("file") MultipartFile file
) throws Exception {
    return materialService.uploadMaterial(classLevel, subjectId, displayName, file);
}

}
