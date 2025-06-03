package com.chatop.api.mapper;

import org.springframework.stereotype.Component;

import com.chatop.api.dto.message.MessageRequestDto;
import com.chatop.api.model.Message;

@Component
public class MessageMapper {

    public Message toEntity(MessageRequestDto messageDto){

        Message messageEntity = new Message();
        messageEntity.setRentalId(messageDto.getRentalId());
        messageEntity.setUserId(messageDto.getUserId());
        messageEntity.setMessage(messageDto.getMessage());

        return messageEntity;

    }

    public MessageRequestDto toDto(Message messageEntity){

        MessageRequestDto messageDto = new MessageRequestDto();
        messageDto.setMessage(messageEntity.getMessage());
        messageDto.setRentalId(messageEntity.getRentalId());
        messageDto.setUserId(messageEntity.getUserId());

        return messageDto;
    }

}
