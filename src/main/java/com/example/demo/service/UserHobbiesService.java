package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Hobbies;
import com.example.demo.entity.UserHobbies;
import com.example.demo.repository.UserHobbiesRepository;

@Service
public class UserHobbiesService {

    @Autowired
    private UserHobbiesRepository userHobbiesRepository;

    public List<UserHobbies> getAllUserHobbies() {
        return userHobbiesRepository.findAll();
    }

    public List<UserHobbies> assignUserHobbies(Long userId, List<Hobbies> hobbies) {
        List<UserHobbies> userHobbiesList = new ArrayList<>();
        for (Hobbies hobby : hobbies) {
            UserHobbies userHobby = userHobbiesRepository.findByUserIdAndHobbyId(userId, hobby.getId());
            if (userHobby == null) {
                userHobby = UserHobbies.builder()
                        .userId(userId)
                        .hobbyId(hobby.getId())
                        .build();
            }
            userHobbiesList.add(userHobby);
        }
        return userHobbiesRepository.saveAll(userHobbiesList);
    }

}
