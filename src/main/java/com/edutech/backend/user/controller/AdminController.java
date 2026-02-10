package com.edutech.backend.user.controller;

import com.edutech.backend.user.dto.AdminSignupRequest;
import com.edutech.backend.user.dto.CreateStudentRequest;
import com.edutech.backend.user.entity.User;
import com.edutech.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PostMapping("/signup")
    public User createAdmin(@RequestBody AdminSignupRequest request) {
        return userService.createAdmin(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
    }
    @PostMapping("/students")
    public User createStudent(@RequestBody CreateStudentRequest request) {
    return userService.createStudent(request);
}
    @GetMapping("/students")
    public List<User> getAllStudents() {
    return userService.getAllStudents();
}

@DeleteMapping("/students/{id}")
public void deleteStudent(@PathVariable Long id) {
    userService.deleteStudent(id);
}



}
