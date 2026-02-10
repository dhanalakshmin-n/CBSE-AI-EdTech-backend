package com.edutech.backend.user.service;

import com.edutech.backend.security.JwtUtil;
import com.edutech.backend.user.dto.CreateStudentRequest;
import com.edutech.backend.user.dto.LoginResponse;
import com.edutech.backend.user.entity.User;
import com.edutech.backend.user.enums.Role;
import com.edutech.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;          //spring marks that this class is a service.



import java.time.LocalDateTime;  //for setting createdAt timestamp when creating a user.

@Service
@RequiredArgsConstructor     //Lombok generates a constructor for final fields automatically.
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public User createAdmin(String name, String email, String password) {

        userRepository.findByEmail(email).ifPresent(user -> {
            throw new RuntimeException("Admin already exists with this email");
        });

        User admin = User.builder()
                .name(name)
                .email(email)
                .password(password) // I need to hash it later.
                .role(Role.ADMIN)
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(admin);
    }

   public LoginResponse login(String email, String password) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    if (!user.getEnabled()) {
        throw new RuntimeException("User account is disabled");
    }

    if (!user.getPassword().equals(password)) {
        throw new RuntimeException("Invalid email or password");
    }

    String token = jwtUtil.generateToken(user);

    return LoginResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .role(user.getRole())
            .token(token)
            .build();
}

    public User createStudent(CreateStudentRequest request) {

    userRepository.findByEmail(request.getEmail())
            .ifPresent(user -> {
                throw new RuntimeException("Student already exists with this email");
            });

    User student = User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(request.getPassword()) // hashing later
            .role(Role.STUDENT)
            .enabled(true)
            .studentClass(request.getStudentClass())
            .schoolName(request.getSchoolName())
            .createdAt(LocalDateTime.now())
            .build();

    return userRepository.save(student);
}

}
