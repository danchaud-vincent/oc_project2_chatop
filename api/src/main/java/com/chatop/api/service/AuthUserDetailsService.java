package com.chatop.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.chatop.api.exception.UserNotFoundException;
import com.chatop.api.model.User;
import com.chatop.api.model.auth.AuthUser;
import com.chatop.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() ->
            new UserNotFoundException(String.format("No user found with the email '%s'", email))
        );
        
        return new AuthUser(user);
    }

}
