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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@SecurityRequirement(name = "bearerAuth")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Messages", description = "Methods on messages")
@RequestMapping("/api")
public class MessagesController {
    
    private final MessageService messageService;

    @Operation(
        summary = "Get all of the messages",
        description = "Get a list of all the messages",
        responses = {
            @ApiResponse(responseCode = "200", description = "Messages found")
        }
    )
    @GetMapping("/messages")
    public ResponseEntity<ResponseMessagesDto> getMessages(){
        return new ResponseEntity<ResponseMessagesDto>(messageService.getMessages(), HttpStatus.OK);
    }

    @Operation(
        summary = "Send a new message",
        description = "Send a new message to a rental's owner",
        responses = {
            @ApiResponse(responseCode = "201", description = "Message sent")
        }
    )
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
