package com.linkedin.userService.services;

import com.linkedin.userService.dto.LoginRequestDTO;
import com.linkedin.userService.dto.SignupResponseDTO;
import com.linkedin.userService.dto.UserDTO;

public interface AuthService {
    UserDTO signUp(SignupResponseDTO signupResponseDTO);

    String login(LoginRequestDTO loginRequestDTO);
}
