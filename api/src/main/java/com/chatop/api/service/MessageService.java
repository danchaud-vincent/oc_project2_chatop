package com.chatop.api.service;

import org.springframework.stereotype.Service;

import com.chatop.api.dto.MessageDto;
import com.chatop.api.dto.response.ResponseMessageDto;
import com.chatop.api.mapper.MessageMapper;
import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public void getMessages(){

    }

    public ResponseMessageDto sendMessage(MessageDto messageDto){

        Message messageEntity = messageMapper.toEntity(messageDto);
        messageRepository.save(messageEntity);
        
        return new ResponseMessageDto(messageDto.getMessage());
    }
}
