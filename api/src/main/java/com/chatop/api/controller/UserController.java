package com.chatop.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.UserDto;
import com.chatop.api.dto.auth.AuthRequestDto;
import com.chatop.api.dto.auth.AuthResponseDto;
import com.chatop.api.dto.auth.RegisterRequestDto;
import com.chatop.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@SecurityRequirement(name = "bearerAuth")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Users", description = "Methods on users")
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "User authentication",
        description = "Allows a user to log in and authenticate",
        responses = {
            @ApiResponse(responseCode = "200", description = "User logged in")
        }
    )
    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequest) {
        return new ResponseEntity<AuthResponseDto>(userService.authenticate(authRequest), HttpStatus.OK);
    }
    
    @Operation(
        summary = "User registration",
        description = "Allows a user to register and logging in",
        responses = {
            @ApiResponse(responseCode = "201", description = "User registered")
        }
    )
    @PostMapping("/auth/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequest) {
        return new ResponseEntity<AuthResponseDto>(userService.register(registerRequest), HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get the information of the logged user",
        description = "Allows you to retrieve all the information of the logged user",
        responses = {
            @ApiResponse(responseCode = "200", description = "Logged user's information")
        }
    )
    @GetMapping("/auth/me")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        return new ResponseEntity<UserDto>(userService.getCurrentUser(authentication), HttpStatus.OK);
    }


    @Operation(
        summary = "Get the information of a user selected by ID",
        description = "Retrieve a user's information by providing an ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "User found")
        }
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        return new ResponseEntity<UserDto>(userService.getUserById(userId), HttpStatus.OK);
    }
    
}
