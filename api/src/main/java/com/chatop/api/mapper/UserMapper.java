package com.chatop.api.mapper;

import org.springframework.stereotype.Component;

import com.chatop.api.dto.UserDto;
import com.chatop.api.model.User;

@Component
public class UserMapper {


    public UserDto toDto(User user){

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;

    }

}
