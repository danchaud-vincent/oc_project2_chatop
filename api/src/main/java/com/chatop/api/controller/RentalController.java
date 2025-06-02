package com.chatop.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chatop.api.dto.RentalCreateDto;
import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalUpdateDto;
import com.chatop.api.dto.response.ResponseRentalDto;
import com.chatop.api.dto.response.ResponseRentalsDto;
import com.chatop.api.model.ErrorResponse;
import com.chatop.api.service.RentalService;
import com.chatop.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@SecurityRequirement(name = "bearerAuth")
@RestController
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Rentals", description = "Methods on rentals")
@RequestMapping("/api")
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;

    @Operation(
        summary = "Get the list of rentals",
        description = "Retrieve the list of rentals with all the information for each rental",
        responses = {
            @ApiResponse(responseCode = "200", description = "List of rentals returned"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    @GetMapping("/rentals")
    public ResponseEntity<ResponseRentalsDto> getRentals(){

        List<RentalDto> rentals = rentalService.getRentals();

        return new ResponseEntity<ResponseRentalsDto>(new ResponseRentalsDto(rentals), HttpStatus.OK);
    }


    @Operation(
        summary = "Get a rental by ID",
        description = "Retrieve the information of a rental by providing its ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Rental found"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    @GetMapping("/rentals/{rentalId}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable int rentalId) {
        return new ResponseEntity<RentalDto>(rentalService.getRentalById(rentalId), HttpStatus.OK);
    }


    @Operation(
        summary = "Create a new rental",
        description = "Create a new rental by providing all the necessary information",
        responses = {
            @ApiResponse(responseCode = "201", description = "Rental added"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    @PostMapping("/rentals")
    public ResponseEntity<ResponseRentalDto> addRental(
        @RequestParam("name") String name,
        @RequestParam("surface") BigDecimal surface,
        @RequestParam("price") BigDecimal price,
        @RequestParam("description") String description,
        @RequestParam("picture") MultipartFile imageFile,
        Authentication authentication) throws IOException{
        
        // get the id of the current user logged in
        Integer ownerID = userService.getCurrentUser(authentication).getId();
        
        // build the url of the app, and then build the url for the image uploaded
        // to display the image with the imageController
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String pictureUrl = baseUrl + "/api/uploads/images/" + imageFile.getOriginalFilename();

        RentalCreateDto newRental = new RentalCreateDto(name, surface, price, description, ownerID, pictureUrl);
        
        rentalService.addRental(newRental, imageFile);
      
        return new ResponseEntity<ResponseRentalDto>(new ResponseRentalDto("Rental created"), HttpStatus.CREATED);
    }


    @Operation(
        summary = "Update a rental",
        description = "Update a rental selected by ID by providing the information to update",
        responses = {
            @ApiResponse(responseCode = "200", description = "Rental updated"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(
                responseCode = "400", 
                description = "Bad request", 
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    @PutMapping("/rentals/{rentalId}")
    public ResponseEntity<ResponseRentalDto> updateRental(
        @PathVariable int rentalId, 
        @RequestParam("name") String name,
        @RequestParam("surface") String surface,
        @RequestParam("price") BigDecimal price,
        @RequestParam("description") String description) {

        RentalUpdateDto rentalUpdatedDto = new RentalUpdateDto(name, price, price, description);
       
        rentalService.updateRental(rentalId, rentalUpdatedDto);

        return new ResponseEntity<ResponseRentalDto>(new ResponseRentalDto("Rental updated"), HttpStatus.OK);
    }
}
