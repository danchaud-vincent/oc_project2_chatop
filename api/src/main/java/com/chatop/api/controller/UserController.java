package com.chatop.api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.auth.AuthRequestDto;
import com.chatop.api.dto.auth.AuthResponseDto;
import com.chatop.api.dto.auth.RegisterRequestDto;
import com.chatop.api.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto authRequest) {
        return userService.authenticate(authRequest);
    }
    

    @PostMapping("/auth/register")
    public String register(@RequestBody RegisterRequestDto registerRequest) {
        return userService.register(registerRequest);
    }

    @GetMapping("/auth/me")
    public String getCurrentUser() {
        return "User";
    }

    @GetMapping("/users")
    public String getUsers() {
        return "Get all the users";
    }

    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable Integer userId) {
        return "Get one user";
    }
    
}
