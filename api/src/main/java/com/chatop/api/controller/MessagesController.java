package com.chatop.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> getMessages(){
        return new ResponseEntity<>("messages", HttpStatus.OK);
    }

    @PostMapping("/messages")
    public String postMethodName(@RequestBody String newMessage) {
        
        messageService.sendMessage(newMessage);
        
        return "hello";
    }
    
}
