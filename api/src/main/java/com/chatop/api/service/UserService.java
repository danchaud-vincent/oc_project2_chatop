package com.chatop.api.service;

import org.springframework.stereotype.Service;

import com.chatop.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

}
