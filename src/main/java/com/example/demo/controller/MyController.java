package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Hobbies;
import com.example.demo.entity.User;
import com.example.demo.entity.UserHobbies;
import com.example.demo.service.HobbiesService;
import com.example.demo.service.UserHobbiesService;
import com.example.demo.service.UserService;
import com.example.demo.shared.ApiResponse;
import com.example.dto.AuthResponse;
import com.example.dto.LoginDto;
import com.example.dto.RegistrationDto;
import com.example.dto.UserHobbiesDto;
import com.example.dto.UserHobbyResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Validated
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private HobbiesService hobbiesService;

    @Autowired
    private UserHobbiesService userHobbiesService;

    @Autowired
    Environment env;

    @GetMapping("/hello")
    public ResponseEntity<ApiResponse<String>> hello() {
        return ResponseEntity.ok(ApiResponse.sucess("Hello, World!"));
    }

    /* post request which except the oayload name,email and phone number */
    @PostMapping("/registration")
    public ResponseEntity<ApiResponse<User>> postExample(
            @Valid @RequestBody RegistrationDto userRequest) {

        String password = userRequest.getPassword();
        String confirmPassword = userRequest.getConfirmPassword();

        if (!password.equals(confirmPassword))
            throw new RuntimeException("Password and Confirm Password do not match");

        // Need to save user to database
        User response = userService.save(userRequest);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.sucess(response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginDto loginRequest) {
        AuthResponse response = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.sucess(response));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.sucess(users));
    }

    @GetMapping("/hobbies")
    public ResponseEntity<ApiResponse<List<Hobbies>>> getHobbies() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.sucess(hobbiesService.getAllHobbies()));
    }

    @GetMapping("/userhobbies")
    public ResponseEntity<ApiResponse<List<UserHobbies>>> getUserHobbies() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.sucess(userHobbiesService.getAllUserHobbies()));
    }

    @PostMapping("/userhobbies/{userId}")
    public ResponseEntity<ApiResponse<UserHobbyResponse>> createUserHobbies(
            @Pattern(regexp = "^[0-9]+", message = "Invalid user ID") @PathVariable Long userId,
            @Valid @RequestBody UserHobbiesDto userHobbiesDto) {

        User user = userService.getUserById(userId);
        if (user == null)
            throw new RuntimeException("User not found");

        List<Hobbies> hobbies = hobbiesService.upsertHobbies(userHobbiesDto.getHobbies());

        // Assigned Hobbies to user
        userHobbiesService.assignUserHobbies(user.getId(), hobbies);

        UserHobbyResponse response = UserHobbyResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .hobbies(hobbies)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.sucess(response));
    }

    @PostMapping("/userhobbies")
    public ResponseEntity<ApiResponse<UserHobbyResponse>> createUserHobbiesByRequestParam(
            @RequestParam(required = true) @Max(value = 9, message = "Invalid user ID") @Min(value = 1, message = "Invalid user ID") Long userId,
            @Valid @RequestBody UserHobbiesDto userHobbiesDto) {

        User user = userService.getUserById(userId);
        if (user == null)
            throw new RuntimeException("User not found");

        List<Hobbies> hobbies = hobbiesService.upsertHobbies(userHobbiesDto.getHobbies());

        // Assigned Hobbies to user
        userHobbiesService.assignUserHobbies(user.getId(), hobbies);

        UserHobbyResponse response = UserHobbyResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .hobbies(hobbies)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.sucess(response));
    }

    @DeleteMapping("/userhobbies/{hobbyName}")
    public ResponseEntity<ApiResponse<String>> deleteHobby(@PathVariable String hobbyName) {
        hobbiesService.delete(hobbyName);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.sucess("Hobby deleted successfully"));
    }

    @GetMapping("/getEnv")
    public String getMethodName(@RequestParam String param) {
        return env.getProperty(param);
    }

}
