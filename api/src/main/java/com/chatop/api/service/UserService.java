package com.chatop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

}
