package com.chatop.api.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.chatop.api.dto.auth.RegisterRequestDto;
import com.chatop.api.dto.auth.UserResponseDto;
import com.chatop.api.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserResponseDto toDto(User user){

        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;

    }

    public User toEntity(RegisterRequestDto userRegistered){
        User user = new User();
        user.setEmail(userRegistered.getEmail());
        user.setName(userRegistered.getName());
        user.setPassword(passwordEncoder.encode(userRegistered.getPassword()));

        return user;
    }

}
