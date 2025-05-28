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
@Tag(name = "Messages", description = "Opérations sur les messages")
@RequestMapping("/api")
public class MessagesController {
    
    private final MessageService messageService;

    @Operation(
        summary = "Récupère l'ensemble des messages",
        description = "Permet de récupèrer la liste des messages",
        responses = {
            @ApiResponse(responseCode = "200", description = "Messages trouvés")
        }
    )
    @GetMapping("/messages")
    public ResponseEntity<ResponseMessagesDto> getMessages(){
        return new ResponseEntity<ResponseMessagesDto>(messageService.getMessages(), HttpStatus.OK);
    }

    @Operation(
        summary = "Envoi un nouveau message",
        description = "Permet d'envoyer un message à un utilisateur",
        responses = {
            @ApiResponse(responseCode = "201", description = "Message envoyé")
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
