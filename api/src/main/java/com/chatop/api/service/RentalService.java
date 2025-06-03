package com.chatop.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chatop.api.dto.image.ImageDto;
import com.chatop.api.dto.rental.RentalCreateRequestDto;
import com.chatop.api.dto.rental.RentalDto;
import com.chatop.api.dto.rental.RentalUpdateRequestDto;
import com.chatop.api.exception.RentalNotFoundException;
import com.chatop.api.exception.UserNotFoundException;
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

        List<Rental> rentals = rentalRepository.findAll();

        List<RentalDto> rentalsDto = new ArrayList<RentalDto>();
        for(Rental rental: rentals){

            RentalDto rentalDto = rentalMapper.toDto(rental);

            rentalsDto.add(rentalDto);
        }

        return rentalsDto;
    }

    public RentalDto getRentalById(int rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new RentalNotFoundException("Rental not found with ID: " + rentalId));

        return rentalMapper.toDto(rental);
    }

    public void addRental(RentalCreateRequestDto rentalDto, MultipartFile imageFile) throws IOException {

        Integer ownerId = rentalDto.getOwnerId();

        User user = userRepository
            .findById(ownerId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + ownerId));

        Rental rental = rentalMapper.toEntity(rentalDto, imageFile);
        rental.setOwner(user);

        // saved the rental 
        Rental savedRental = rentalRepository.save(rental);

        // generate the image endpoint of the rental based on its id;
        Integer rentalId = savedRental.getId();
        String pictureUrl = generatePictureUrl(rentalId);
        savedRental.setPicture(pictureUrl);

        // save the rental again with the url based on the rental id
        rentalRepository.save(savedRental);
        
    }

    public void updateRental(int rentalId, RentalUpdateRequestDto rentalUpdatedDto) {

        Rental oldRental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new RentalNotFoundException("Rental not found with ID " + rentalId));

        Rental updatedRental = rentalMapper.updateEntity(oldRental, rentalUpdatedDto);

        rentalRepository.save(updatedRental);
    }

    private String generatePictureUrl(Integer rentalId){
        // build the endpoint for the image of a rental selected by id
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String pictureUrl = baseUrl + "/api/rentals/images/" + rentalId;

        return pictureUrl;
    }

    public ImageDto getImageRentalById(Integer rentalId) {
        RentalDto rental = getRentalById(rentalId);

        ImageDto imageDto = new ImageDto(
            rental.getPictureName(), 
            rental.getPictureType(),
            rental.getPictureData()
        );

        return imageDto;
    }


}
