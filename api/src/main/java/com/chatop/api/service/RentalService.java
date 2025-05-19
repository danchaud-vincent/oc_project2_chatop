package com.chatop.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalUpdateDto;
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

    public List<RentalDto> getRentals() {

        List<RentalDto> rentalsDto = new ArrayList<RentalDto>();

        List<Rental> rentals = rentalRepository.findAll();

        for(Rental rental: rentals){

            RentalDto rentalDto = toDto(rental);

            rentalsDto.add(rentalDto);
        }

        return rentalsDto;
    }

     public RentalDto getRentalById(int rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new RuntimeException("Rental not found with ID: " + rentalId));

        return toDto(rental);
    }

    public Rental addRental(Rental rental) {

        Integer ownerId = rental.getOwner().getId();

        User user = userRepository
            .findById(ownerId)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + ownerId));

        
        rental.setOwner(user);

        return rentalRepository.save(rental);
        
    }

    public Rental updateRental(int rentalId, RentalUpdateDto rentalDto) {

        Rental rental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new RuntimeException("Rental not found with ID " + rentalId));

        Integer ownerId = rental.getOwner().getId();

        User user = userRepository
                .findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + ownerId));

        
        rental.setName(rentalDto.getName());
        rental.setSurface(rentalDto.getSurface()); 
        rental.setPrice(rentalDto.getPrice());
        rental.setDescription(rentalDto.getDescription());
        rental.setOwner(user);

        return rentalRepository.save(rental);
    }

    private RentalDto toDto(Rental rental){
        RentalDto rentalDto = new RentalDto();

        rentalDto.setId(rental.getId());
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
