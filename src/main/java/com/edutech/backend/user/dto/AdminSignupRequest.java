package com.edutech.backend.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminSignupRequest {
    private String name;
    private String email;
    private String password;
}
