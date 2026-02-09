package com.edutech.backend.user.service;

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
}
