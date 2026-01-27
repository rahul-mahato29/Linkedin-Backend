package com.linkedin.userService.services.impl;

import com.linkedin.userService.dto.LoginRequestDTO;
import com.linkedin.userService.dto.SignupResponseDTO;
import com.linkedin.userService.dto.UserDTO;
import com.linkedin.userService.entities.User;
import com.linkedin.userService.exceptions.BadRequestException;
import com.linkedin.userService.exceptions.ResourceNotFoundException;
import com.linkedin.userService.repositories.UserRepository;
import com.linkedin.userService.security.JWTService;
import com.linkedin.userService.services.AuthService;
import com.linkedin.userService.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;

    @Override
    public UserDTO signUp(SignupResponseDTO signupResponseDTO) {

        User user = modelMapper.map(signupResponseDTO, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupResponseDTO.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email : "+loginRequestDTO.getEmail()));

        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDTO.getPassword(), user.getPassword());
        if(!isPasswordMatch) {
            throw new BadRequestException("Incorrect Password");
        }

        return jwtService.generateAccessToken(user);
    }
}
