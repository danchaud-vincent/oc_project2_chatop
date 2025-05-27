package com.chatop.api.mapper;

import org.springframework.stereotype.Component;

import com.chatop.api.dto.MessageDto;
import com.chatop.api.model.Message;

@Component
public class MessageMapper {

    public Message toEntity(MessageDto messageDto){

        Message messageEntity = new Message();
        messageEntity.setRentalId(messageDto.getRentalId());
        messageEntity.setUserId(messageDto.getUserId());
        messageEntity.setMessage(messageDto.getMessage());

        return messageEntity;

    }

    public MessageDto toDto(Message messageEntity){

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage(messageEntity.getMessage());
        messageDto.setRentalId(messageEntity.getRentalId());
        messageDto.setUserId(messageDto.getUserId());

        return messageDto;
    }

}
