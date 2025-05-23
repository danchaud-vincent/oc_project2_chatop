package com.chatop.api.service;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.chatop.api.dto.UserDto;
import com.chatop.api.dto.auth.AuthRequestDto;
import com.chatop.api.dto.auth.AuthResponseDto;
import com.chatop.api.dto.auth.RegisterRequestDto;
import com.chatop.api.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public AuthResponseDto authenticate(AuthRequestDto authRequestDto){

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequestDto.getLogin(), 
                authRequestDto.getPassword()
            )
        );

        String email = authRequestDto.getLogin();

        if (!authentication.isAuthenticated()) {
            throw new RuntimeException(String.format("Unable to generate a token for the email '%s'.", email));
        }

        String token = jwtService.generateToken(email);
        
        return new AuthResponseDto(token);
    }

    public AuthResponseDto register(RegisterRequestDto registerRequest){

        String email = registerRequest.getEmail();

        Optional<User> existingUser = userRepository.findByEmail(email);
        
        if (existingUser.isPresent()) {
            throw new RuntimeException(String.format("User with the email '%s' already exists.", email));
        }

        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setName(registerRequest.getName());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(newUser);

        // Authenticate the new user directly
        AuthRequestDto authRequest = new AuthRequestDto(
            registerRequest.getEmail(), 
            registerRequest.getPassword()
        );

        return authenticate(authRequest);
    }

    public UserDto getCurrentUser(Authentication authentication) {

        User currentUser = userRepository.findByEmail(authentication.getName()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        return userMapper.toDto(currentUser);
    }

}
