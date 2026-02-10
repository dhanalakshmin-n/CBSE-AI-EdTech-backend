package com.edutech.backend.user.dto;

import com.edutech.backend.user.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;

    private String token; // JWT
}
