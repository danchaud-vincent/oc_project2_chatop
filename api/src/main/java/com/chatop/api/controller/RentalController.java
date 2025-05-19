package com.chatop.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.RentalDto;
import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<RentalDto> getRentals(){

        return rentalService.getRentals();
    }

    @GetMapping("/rentals/{rentalId}")
    public RentalDto getRentalById(@PathVariable int rentalId) {
        return rentalService.getRentalById(rentalId);
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
    public String updateRental(@PathVariable int rentalId, @RequestBody RentalDto rentalDto) {
       
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
