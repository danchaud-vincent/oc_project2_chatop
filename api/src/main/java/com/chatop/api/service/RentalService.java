package com.chatop.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.dto.RentalCreateDto;
import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalUpdateDto;
import com.chatop.api.mapper.RentalMapper;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final RentalMapper rentalMapper;

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

    public void addRental(RentalCreateDto rentalDto, MultipartFile imageFile) throws IOException {

        Integer ownerId = rentalDto.getOwnerId();

        User user = userRepository
            .findById(ownerId)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + ownerId));

        Rental rental = rentalMapper.toEntity(rentalDto);
        rental.setOwner(user);
        rental.setPicture(imageFile.getOriginalFilename());
        rental.setPictureType(imageFile.getContentType());
        rental.setPictureData(imageFile.getBytes());

        rentalRepository.save(rental);
    }

    public void updateRental(int rentalId, RentalUpdateDto rentalUpdatedDto) {

        Rental oldRental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new RuntimeException("Rental not found with ID " + rentalId));

        Rental updatedRental = rentalMapper.updateEntity(oldRental, rentalUpdatedDto);

        rentalRepository.save(updatedRental);
    }


}
