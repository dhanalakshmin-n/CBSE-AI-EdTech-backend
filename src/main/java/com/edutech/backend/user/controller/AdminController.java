package com.edutech.backend.user.controller;

import com.edutech.backend.user.dto.AdminSignupRequest;
import com.edutech.backend.user.entity.User;
import com.edutech.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
