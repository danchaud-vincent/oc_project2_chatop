package com.chatop.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.UserDto;
import com.chatop.api.dto.auth.AuthRequestDto;
import com.chatop.api.dto.auth.AuthResponseDto;
import com.chatop.api.dto.auth.RegisterRequestDto;
import com.chatop.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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



@RestController
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Utilisateurs", description = "Opérations sur les utilisateurs")
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "Connexion et authentification d'un utilisateur",
        description = "Permet de connecter et d'authentifier un utilisateur",
        responses = {
            @ApiResponse(responseCode = "200", description = "Utilisateur connecté")
        }
    )
    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequest) {
        return new ResponseEntity<AuthResponseDto>(userService.authenticate(authRequest), HttpStatus.OK);
    }
    
    @Operation(
        summary = "Enregistrement d'un utilisateur",
        description = "Permet d'enregistrer un nouvel utilisateur, et de le connecter",
        responses = {
            @ApiResponse(responseCode = "201", description = "Nouvel Utilisateur créé")
        }
    )
    @PostMapping("/auth/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequest) {
        return new ResponseEntity<AuthResponseDto>(userService.register(registerRequest), HttpStatus.CREATED);
    }

    @Operation(
        summary = "Récupère les information de l'utilisateur connecté",
        description = "Permet de récupérer l'ensemble des informations de l'utilisateur connecté",
        responses = {
            @ApiResponse(responseCode = "200", description = "Informations de l'utilisateur retournées")
        }
    )
    @GetMapping("/auth/me")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        return new ResponseEntity<UserDto>(userService.getCurrentUser(authentication), HttpStatus.OK);
    }


    @Operation(
        summary = "Récumère les informations d'un utilisateur",
        description = "Permet de récupérer les informations d'un utilisateur en fournissant son ID ",
        responses = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé")
        }
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        return new ResponseEntity<UserDto>(userService.getUserById(userId), HttpStatus.OK);
    }
    
}
