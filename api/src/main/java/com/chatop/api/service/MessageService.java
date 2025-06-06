package com.chatop.api.service;

import org.springframework.stereotype.Service;

import com.chatop.api.dto.message.MessageRequestDto;
import com.chatop.api.dto.message.MessageResponseDto;
import com.chatop.api.exception.RentalNotFoundException;
import com.chatop.api.exception.UserNotFoundException;
import com.chatop.api.mapper.MessageMapper;
import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public MessageResponseDto sendMessage(MessageRequestDto messageDto){

        // check if the user exist
        userRepository.findById(messageDto.getUserId()).orElseThrow(() ->
            new UserNotFoundException("User not found with id : " + messageDto.getUserId())
        );

        // check if the rental exist
        rentalRepository.findById(messageDto.getRentalId()).orElseThrow(() ->
            new RentalNotFoundException("Rental not found with id : " + messageDto.getRentalId())
        );

        Message messageEntity = messageMapper.toEntity(messageDto);
        messageRepository.save(messageEntity);
        
        return new MessageResponseDto(messageDto.getMessage());
    }
}
