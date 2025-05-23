package com.chatop.api.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.auth.AuthRequestDto;
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

    public String verify(AuthRequestDto authRequestDto){

        return "fail";
    }

    public String register(RegisterRequestDto registerRequest){

        if (emailExist(registerRequest.getEmail())){
            throw new RuntimeException("There is already an account with the email address: " + registerRequest.getEmail());
        }

        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setName(registerRequest.getName());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(newUser);

        return "register";
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
