package com.chatop.api.mapper;

import org.springframework.stereotype.Component;
import com.chatop.api.dto.auth.UserResponseDto;
import com.chatop.api.model.User;

@Component
public class UserMapper {


    public UserResponseDto toDto(User user){

        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;

    }

}
