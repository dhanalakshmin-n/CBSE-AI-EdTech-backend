package com.edutech.backend.syllabus.controller;

import com.edutech.backend.syllabus.entity.Material;
import com.edutech.backend.syllabus.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.util.List;
import com.edutech.backend.syllabus.dto.UpdateMaterialRequest;



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

//get material by class

@GetMapping("/class/{classLevel}")
public List<Material> getMaterialsByClass(
        @PathVariable Integer classLevel
) {
    return materialService.getMaterialsByClass(classLevel);
}

//delete material by id
@DeleteMapping("/{id}")
public String deleteMaterial(@PathVariable Long id) {
    materialService.deleteMaterial(id);
    return "Material deleted successfully";
}

//update material by id
@PutMapping("/{id}")
public Material updateMaterial(
        @PathVariable Long id,
        @RequestBody UpdateMaterialRequest request
) {
    return materialService.updateMaterial(id, request.getDisplayName());
}


}
