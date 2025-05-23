package com.chatop.api.mapper;

import org.springframework.stereotype.Component;

import com.chatop.api.dto.RentalCreateDto;
import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalUpdateDto;
import com.chatop.api.model.Rental;

@Component
public class RentalMapper {


    public RentalDto toDto(Rental rental){
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

    public Rental toEntity(RentalCreateDto rentalDto){
        
        Rental rental = new Rental();

        rental.setName(rentalDto.getName());
        rental.setDescription(rentalDto.getDescription());
        rental.setSurface(rentalDto.getSurface());
        rental.setPrice(rentalDto.getPrice());
        
        return rental;
    }


    public Rental updateEntity(Rental rental, RentalUpdateDto rentalUpdatedDto){

        rental.setName(rentalUpdatedDto.getName());
        rental.setSurface(rentalUpdatedDto.getSurface()); 
        rental.setPrice(rentalUpdatedDto.getPrice());
        rental.setDescription(rentalUpdatedDto.getDescription());

        return rental;
    }

}
