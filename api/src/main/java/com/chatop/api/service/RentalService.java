package com.chatop.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.RentalCreateDto;
import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalUpdateDto;
import com.chatop.api.mapper.RentalMapper;
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

    @Autowired
    RentalMapper rentalMapper;


    public List<RentalDto> getRentals() {

        List<RentalDto> rentalsDto = new ArrayList<RentalDto>();

        List<Rental> rentals = rentalRepository.findAll();

        for(Rental rental: rentals){

            RentalDto rentalDto = rentalMapper.toDto(rental);

            rentalsDto.add(rentalDto);
        }

        return rentalsDto;
    }

     public RentalDto getRentalById(int rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new RuntimeException("Rental not found with ID: " + rentalId));

        return rentalMapper.toDto(rental);
    }

    public Rental addRental(RentalCreateDto rentalDto) {

        Integer ownerId = rentalDto.getOwnerId();

        User user = userRepository
            .findById(ownerId)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + ownerId));

        Rental rental = rentalMapper.toEntity(rentalDto);
        rental.setOwner(user);
        
        return rentalRepository.save(rental);
    }

    public Rental updateRental(int rentalId, RentalUpdateDto rentalUpdatedDto) {

        Rental oldRental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new RuntimeException("Rental not found with ID " + rentalId));

        Rental updatedRental = rentalMapper.updateEntity(oldRental, rentalUpdatedDto);

        return rentalRepository.save(updatedRental);
    }


}
