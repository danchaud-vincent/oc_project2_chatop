package com.chatop.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.auth.AuthRequestDto;
import com.chatop.api.dto.auth.AuthResponseDto;
import com.chatop.api.dto.auth.RegisterRequestDto;
import com.chatop.api.dto.auth.UserResponseDto;
import com.chatop.api.model.ErrorResponse;
import com.chatop.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
            @ApiResponse(responseCode = "200", description = "User logged in"),
            @ApiResponse(
                responseCode = "401", 
                description = "Invalid credentials",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto authRequest) {
        return new ResponseEntity<AuthResponseDto>(userService.authenticate(authRequest), HttpStatus.OK);
    }
    
    @Operation(
        summary = "User registration",
        description = "Allows a user to register and logging in",
        responses = {
            @ApiResponse(responseCode = "201", description = "User registered"),
            @ApiResponse(
                responseCode = "401", 
                description = "Invalid credentials",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "409", 
                description = "User already exists",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    @PostMapping("/auth/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        return new ResponseEntity<AuthResponseDto>(userService.register(registerRequest), HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get the information of the logged user",
        description = "Allows you to retrieve all the information of the logged user",
        responses = {
            @ApiResponse(responseCode = "200", description = "Logged user's information"),
            @ApiResponse(
                responseCode = "401", 
                description = "Invalid credentials",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "404", 
                description = "User not found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    @GetMapping("/auth/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(Authentication authentication) {
        return new ResponseEntity<UserResponseDto>(userService.getCurrentUser(authentication), HttpStatus.OK);
    }


    @Operation(
        summary = "Get the information of a user selected by ID",
        description = "Retrieve a user's information by providing an ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(
                responseCode = "401", 
                description = "Invalid credentials",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "404", 
                description = "User not found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Integer userId) {
        return new ResponseEntity<UserResponseDto>(userService.getUserById(userId), HttpStatus.OK);
    }
    
}
