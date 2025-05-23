package com.chatop.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalUpdateDto;
import com.chatop.api.service.RentalService;
import com.chatop.api.service.UserService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;

    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDto>> getRentals(){
        return new ResponseEntity<List<RentalDto>>(rentalService.getRentals(), HttpStatus.OK);
    }

    @GetMapping("/rentals/{rentalId}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable int rentalId) {
        return new ResponseEntity<RentalDto>(rentalService.getRentalById(rentalId), HttpStatus.OK);
    }

    @PostMapping("/rentals")
    public ResponseEntity<Map<String, String>> addRental(
        @RequestParam("name") String name,
        @RequestParam("surface") String surface,
        @RequestParam("price") String price,
        @RequestParam("description") String description,
        @RequestParam("picture") MultipartFile imageFile,
        Authentication authentication) throws IOException {
        
        Integer ownerID = userService.getCurrentUser(authentication).getId();
        System.out.println(ownerID);
        // RentalCreateDto newRental = new RentalCreateDto(name, surface, price, description, ownerID);
        
        // rentalService.addRental(newRental, imageFile);
      
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", "Rental created!"));
    }

    @PutMapping("/rentals/{rentalId}")
    public ResponseEntity<Map<String, String>> updateRental(@PathVariable int rentalId, @RequestBody RentalUpdateDto rentalDto) {
       
        rentalService.updateRental(rentalId, rentalDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("message", "Rental updated!"));
        
    }
}
