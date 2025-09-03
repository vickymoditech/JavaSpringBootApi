package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserHobbies;

public interface UserHobbiesRepository extends JpaRepository<UserHobbies, Long> {
    public UserHobbies findByUserIdAndHobbyId(Long userId, Long hobbyId);
}
