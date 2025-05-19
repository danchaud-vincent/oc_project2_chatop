package com.chatop.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalUpdateDto;
import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDto>> getRentals(){
        return new ResponseEntity<List<RentalDto>>(rentalService.getRentals(), HttpStatus.OK);
    }

    @GetMapping("/rentals/{rentalId}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable int rentalId) {
        return new ResponseEntity<RentalDto>(rentalService.getRentalById(rentalId), HttpStatus.OK);
    }

    @PostMapping("/rentals")
    public ResponseEntity<String> addRental(@RequestBody RentalDto rentalDto) {

        Rental newRental = rentalService.addRental(rentalDto);

        return new ResponseEntity<String>("Rental created!", HttpStatus.OK);
    }

    @PutMapping("/rentals/{rentalId}")
    public String updateRental(@PathVariable int rentalId, @RequestBody RentalUpdateDto rentalDto) {
       
        Rental updatedRental = null;

        try {
            updatedRental = rentalService.updateRental(rentalId, rentalDto);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (updatedRental != null){
            return "Updated!";
        }
        else{
            return "Failed to update!";
        }
    }

}
