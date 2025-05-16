package com.chatop.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class RentalController {

    @GetMapping("/rentals")
    public String getRentals(){
        return "Get the rentals";
    }

    @GetMapping("/rentals/{rentalId}")
    public String getRentalById(@PathVariable int rentalId) {
        return "a rental";
    }

    @PostMapping("/rentals")
    public String addRental(@RequestBody String entity) {
    
        return "Add a rental to rentals";
    }

}
