package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Hobbies;
import com.example.demo.repository.HobbiesRepository;

@Service
public class HobbiesService {

    @Autowired
    private HobbiesRepository hobbiesRepository;

    public List<Hobbies> getAllHobbies() {
        return hobbiesRepository.findAll();
    }

    public List<Hobbies> upsertHobbies(List<String> hobbyNames) {
        List<Hobbies> hobbiesList = new ArrayList<>();
        for (String name : hobbyNames) {
            Hobbies hobby = hobbiesRepository.findByName(name);
            if (hobby == null) {
                hobby = Hobbies.builder()
                        .name(name)
                        .build();
                hobby = hobbiesRepository.save(hobby);
            }
            hobbiesList.add(hobby);
        }
        return hobbiesList;
    }

    public void delete(String hobbyName) {
        Hobbies temp = hobbiesRepository.findByName(hobbyName);

        if (temp == null) {
            throw new RuntimeException("Hobby not found");
        }

        hobbiesRepository.delete(temp);
    }

}
