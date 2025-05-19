package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public String login(@RequestBody String entity) {
        
        return "Logged in!";
    }
    

    @PostMapping("/auth/register")
    public String register(@RequestBody String entity) {
        
        return "Registered!";
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
