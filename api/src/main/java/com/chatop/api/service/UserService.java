package com.chatop.api.service;


import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.auth.AuthRequestDto;
import com.chatop.api.dto.auth.AuthResponseDto;
import com.chatop.api.dto.auth.RegisterRequestDto;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthResponseDto authenticate(AuthRequestDto authRequestDto){

        return new AuthResponseDto();
    }

    public String register(RegisterRequestDto registerRequest){

        String email = registerRequest.getEmail();

        User existingUser = userRepository.findByEmail(email).orElseThrow(() ->
            new RuntimeException(String.format("User with the email '%s' already exists.", email))
        );

        User newUser = new User();
        newUser.setEmail(existingUser.getEmail());
        newUser.setName(existingUser.getName());
        newUser.setPassword(passwordEncoder.encode(existingUser.getPassword()));

        userRepository.save(newUser);

        return "register";
    }

}
