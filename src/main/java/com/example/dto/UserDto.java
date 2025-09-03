package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String name;
    private String email;
    private Long Id;
    private String phoneNumber;
}
