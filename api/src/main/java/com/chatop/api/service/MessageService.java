package com.chatop.api.service;

import org.springframework.stereotype.Service;

import com.chatop.api.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void getMessages(){

    }

    public String sendMessage(String message){
        

        return "Message";
    }
}
