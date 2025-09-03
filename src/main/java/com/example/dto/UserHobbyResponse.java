package com.example.dto;

import java.util.List;

import com.example.demo.entity.Hobbies;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserHobbyResponse {
    private Long userId;
    private String name;
    private List<Hobbies> hobbies;
}
