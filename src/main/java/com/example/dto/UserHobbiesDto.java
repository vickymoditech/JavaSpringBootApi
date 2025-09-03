package com.example.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserHobbiesDto {
    // @NotNull(message = "UserId cannot be empty")
    // private Long userId;

    @NotNull(message = "Hobbies cannot be null")
    @NotEmpty(message = "Hobbies cannot be empty")
    private List<String> hobbies;
}
