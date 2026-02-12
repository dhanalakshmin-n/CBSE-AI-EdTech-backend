package com.edutech.backend.syllabus.service;

import com.edutech.backend.syllabus.entity.Material;
import com.edutech.backend.syllabus.entity.Subject;
import com.edutech.backend.syllabus.repository.MaterialRepository;
import com.edutech.backend.syllabus.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import com.edutech.backend.user.entity.User;
import com.edutech.backend.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    


 public Material uploadMaterial(
        Integer classLevel,
        Long subjectId,
        String displayName,
        MultipartFile file
) throws IOException {

    // Validate subject exists
    Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new RuntimeException("Subject not found"));

    // Create upload directory if not exists
    Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads", "materials");
    if (!Files.exists(uploadDir)) {
        Files.createDirectories(uploadDir);
    }

    // Create file path
    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    Path filePath = uploadDir.resolve(fileName);

    // Save file to disk
    file.transferTo(filePath.toFile());

    // Save metadata to DB
    Material material = Material.builder()
            .classLevel(classLevel)
            .subject(subject)
            .displayName(displayName)
            .fileName(fileName)
            .filePath(filePath.toString())
            .uploadedAt(LocalDateTime.now())   
            .build();

    return materialRepository.save(material);
}

    //get material by class logic
    public List<Material> getMaterialsByClass(Integer classLevel) {
    return materialRepository.findByClassLevel(classLevel);
}

    //delete material by id logic
public void deleteMaterial(Long materialId) {

    Material material = materialRepository.findById(materialId)
            .orElseThrow(() -> new RuntimeException("Material not found"));

    // delete file from disk
    Path filePath = Paths.get(material.getFilePath());

    try {
        Files.deleteIfExists(filePath);
    } catch (Exception e) {
        throw new RuntimeException("Failed to delete file from disk");
    }

    // delete from DB
    materialRepository.delete(material);
}

//update material logic
public Material updateMaterial(Long id, String displayName) {

    Material material = materialRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Material not found"));

    material.setDisplayName(displayName);

    return materialRepository.save(material);
}

//get material by id logic
public List<Material> getMaterialsForStudent(Long userId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (user.getStudentClass() == null) {
        throw new RuntimeException("Student class not assigned");
    }

    Integer classLevel = Integer.parseInt(user.getStudentClass());

    return materialRepository.findByClassLevel(classLevel);
}

//get material by id logic with subject filter

public List<Material> getMaterialsForStudent(Long userId, Long subjectId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (user.getStudentClass() == null) {
        throw new RuntimeException("Student class not assigned");
    }

    Integer classLevel = Integer.parseInt(user.getStudentClass());

    if (subjectId != null) {
        return materialRepository.findByClassLevelAndSubject_Id(classLevel, subjectId);

    }

    return materialRepository.findByClassLevel(classLevel);
}
//view material logic

public Material getMaterialForStudent(Long userId, Long materialId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Integer classLevel = Integer.parseInt(user.getStudentClass());

    Material material = materialRepository.findById(materialId)
            .orElseThrow(() -> new RuntimeException("Material not found"));

    if (!material.getClassLevel().equals(classLevel)) {
        throw new RuntimeException("Unauthorized access to material");
    }

    return material;
}



}

