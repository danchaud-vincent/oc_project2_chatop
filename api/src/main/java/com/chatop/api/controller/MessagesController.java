package com.chatop.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.message.MessageRequestDto;
import com.chatop.api.dto.message.MessageResponseDto;
import com.chatop.api.model.ErrorResponse;
import com.chatop.api.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
        summary = "Send a new message",
        description = "Send a new message to a rental's owner",
        responses = {
            @ApiResponse(responseCode = "201", description = "Message sent"),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    @PostMapping("/messages")
    public ResponseEntity<MessageResponseDto> sendMessage(
        @Valid @RequestBody MessageRequestDto messageRequest) {
        
        MessageResponseDto responseMessageDto = messageService.sendMessage(messageRequest);
        
        return new ResponseEntity<MessageResponseDto>(responseMessageDto, HttpStatus.CREATED);
    }
    
}
