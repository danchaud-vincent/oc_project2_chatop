package com.chatop.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.MessageDto;
import com.chatop.api.dto.response.ResponseMessageDto;
import com.chatop.api.dto.response.ResponseMessagesDto;
import com.chatop.api.service.MessageService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessagesController {
    
    private final MessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity<ResponseMessagesDto> getMessages(){
        return new ResponseEntity<ResponseMessagesDto>(messageService.getMessages(), HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<ResponseMessageDto> sendMessage(
        @RequestBody MessageDto messageRequest) {

        MessageDto messageDto = new MessageDto(
            messageRequest.getUserId(), 
            messageRequest.getRentalId(), 
            messageRequest.getMessage());
        
        ResponseMessageDto responseMessageDto = messageService.sendMessage(messageDto);
        
        return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
    }
    
}
