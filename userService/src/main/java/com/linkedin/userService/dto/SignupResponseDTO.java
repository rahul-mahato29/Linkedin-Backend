package com.linkedin.userService.dto;

import lombok.Data;

@Data
public class SignupResponseDTO {
    private String name;
    private String email;
    private String password;
}
