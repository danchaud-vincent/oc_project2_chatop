package com.chatop.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;


@Service
public class RentalService {

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    UserRepository userRepository;

    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public Rental addRental(Rental rental) {

        User user = userRepository
            .findById(rental.getOwner().getId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        
        rental.setOwner(user);
        rental.setCreatedAt(new Date());
        rental.setUpdatedAt(new Date());

        return rentalRepository.save(rental);
        
    }

    public Rental updateRental(int rentalId, Rental rental) {

        User user = userRepository
                .findById(rental.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        rental.setOwner(user);

        return rentalRepository.save(rental);
    }



}
