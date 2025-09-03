package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    @NotEmpty(message = "Name must be between 2 and 50 characters")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotEmpty(message = "Please provide a valid email address")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Phone number must be exactly 10 digits")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotEmpty(message = "Password cannot be empty")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotEmpty(message = "Confirm Password cannot be empty")
    @NotBlank(message = "Confirm Password is mandatory")
    private String confirmPassword;
}
