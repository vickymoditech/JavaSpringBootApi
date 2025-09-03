package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtProperties;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.dto.AuthResponse;
import com.example.dto.RegistrationDto;
import com.example.dto.UserDto;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtProperties jwtProperties;

    public User save(RegistrationDto userDto) {

        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);

        if (user != null)
            throw new RuntimeException("User already exists");

        user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword()) // In a real application, ensure to hash the password
                .build();
        return userRepository.save(user);
    }

    public AuthResponse login(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null)
            throw new RuntimeException("Invalid email or password");

        String token = jwtService.generateToken(user);

        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresIn(jwtProperties.getExpiration())
                .user(UserDto.builder()
                        .Id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .build();

        return authResponse;

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

}
