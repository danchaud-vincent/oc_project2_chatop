package com.chatop.api.service;


import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.auth.AuthRequestDto;
import com.chatop.api.dto.auth.AuthResponseDto;
import com.chatop.api.dto.auth.RegisterRequestDto;
import com.chatop.api.dto.auth.UserResponseDto;
import com.chatop.api.exception.InvalidCredentialsException;
import com.chatop.api.exception.UserAlreadyExistsException;
import com.chatop.api.exception.UserNotFoundException;
import com.chatop.api.mapper.UserMapper;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserMapper userMapper;

    public AuthResponseDto authenticate(AuthRequestDto authRequestDto){

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequestDto.getEmail(), 
                    authRequestDto.getPassword()
                )
            );
        } 
        catch (Exception e){
            throw new InvalidCredentialsException("Authentication failed");
        }
       
        String email = authRequestDto.getEmail();
        String token = jwtService.generateToken(email);
        
        return new AuthResponseDto(token);
    }

    public AuthResponseDto register(RegisterRequestDto registerRequest){

        String email = registerRequest.getEmail();

        Optional<User> existingUser = userRepository.findByEmail(email);
        
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with the email '%s' already exists.", email));
        }

        User newUser = userMapper.toEntity(registerRequest);

        userRepository.save(newUser);

        // Generate a token
        String token = jwtService.generateToken(newUser.getEmail());
        
        return new AuthResponseDto(token);
    }

    public UserResponseDto getCurrentUser(Authentication authentication) {

        User currentUser = userRepository.findByEmail(authentication.getName()).orElseThrow(() ->
            new UserNotFoundException("User not found")
        );

        return userMapper.toDto(currentUser);
    }

    public UserResponseDto getUserById(Integer userId) {
        
        User user = userRepository.findById(userId).orElseThrow(() ->
            new UserNotFoundException(String.format("User with the id '%s' not found", userId.toString()))
        );

        return userMapper.toDto(user);
    }

}
