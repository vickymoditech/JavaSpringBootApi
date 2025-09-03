package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Hobbies;

public interface HobbiesRepository extends JpaRepository<Hobbies, Long> {
    public Hobbies findByName(String name);
}
