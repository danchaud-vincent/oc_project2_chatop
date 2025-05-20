package com.chatop.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.RegisterDto;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterDto registerDto){

        User newUser = new User();
        newUser.setEmail(registerDto.getEmail());
        newUser.setName(registerDto.getName());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        userRepository.save(newUser);

        return "register";
    }

}
