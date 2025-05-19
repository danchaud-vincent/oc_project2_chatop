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
    public ResponseEntity<?> getRentalById(@PathVariable int rentalId) {

        try{
            return new ResponseEntity<RentalDto>(rentalService.getRentalById(rentalId), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }  
    }

    @PostMapping("/rentals")
    public Rental addRental(@RequestBody Rental rental) {

        try{
            Rental newRental = rentalService.addRental(rental);

            return newRental;
        }
        catch (Exception e){
            System.out.println(e.getMessage());

            return new Rental();
        } 
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
