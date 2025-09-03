package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "Email is mandatory")
    @NotEmpty(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
