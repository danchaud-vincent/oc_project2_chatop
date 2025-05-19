package com.chatop.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.RentalDto;
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

        return rentalRepository.save(rental);
        
    }

    public RentalDto updateRental(int rentalId, Rental rental) {

        User user = userRepository
                .findById(rental.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        rental.setOwner(user);
        rentalRepository.save(rental);

        return toDto(rental);
    }

    private RentalDto toDto(Rental rental){
        RentalDto rentalDto = new RentalDto();

        rentalDto.setName(rental.getName());
        rentalDto.setSurface(rental.getSurface());
        rentalDto.setPrice(rental.getPrice());
        rentalDto.setDescription(rental.getDescription());
        rentalDto.setPicture(rental.getPicture());
        rentalDto.setOwnerId(rental.getOwner().getId());
        rentalDto.setCreatedAt(rental.getCreatedAt());
        rentalDto.setUpdatedat(rental.getUpdatedAt());

        return rentalDto;
    }



}
