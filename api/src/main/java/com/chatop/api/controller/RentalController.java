package com.chatop.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.dto.image.ImageDto;
import com.chatop.api.dto.rental.RentalCreateRequestDto;
import com.chatop.api.dto.rental.RentalCreateResponseDto;
import com.chatop.api.dto.rental.RentalDto;
import com.chatop.api.dto.rental.RentalUpdateRequestDto;
import com.chatop.api.dto.rental.RentalUpdateResponseDto;
import com.chatop.api.dto.rental.RentalsResponseDto;
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
import org.springframework.http.MediaType;
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
            @ApiResponse(
                responseCode = "401", 
                description = "Invalid credentials",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
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
    public ResponseEntity<RentalsResponseDto> getRentals(){

        List<RentalDto> rentals = rentalService.getRentals();

        return new ResponseEntity<RentalsResponseDto>(new RentalsResponseDto(rentals), HttpStatus.OK);
    }


    @Operation(
        summary = "Get a rental by ID",
        description = "Retrieve the information of a rental by providing its ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Rental found"),
            @ApiResponse(
                responseCode = "401", 
                description = "Invalid credentials",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "404", 
                description = "Rental not found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
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
            @ApiResponse(
                responseCode = "401", 
                description = "Invalid credentials",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
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
    public ResponseEntity<RentalCreateResponseDto> addRental(
        @RequestParam("name") String name,
        @RequestParam("surface") BigDecimal surface,
        @RequestParam("price") BigDecimal price,
        @RequestParam("description") String description,
        @RequestParam("picture") MultipartFile imageFile,
        Authentication authentication) throws IOException{
        
        // get the id of the current user logged in
        Integer ownerID = userService.getCurrentUser(authentication).getId();

        RentalCreateRequestDto newRental = new RentalCreateRequestDto(name, surface, price, description, ownerID);
        
        rentalService.addRental(newRental, imageFile);
      
        return new ResponseEntity<RentalCreateResponseDto>(new RentalCreateResponseDto("Rental created"), HttpStatus.CREATED);
    }


    @Operation(
        summary = "Get the image of a rental",
        description = "Get the image of a rental selected by ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Image found"),
            @ApiResponse(
                responseCode = "401", 
                description = "Invalid credentials",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
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
    @GetMapping("/rentals/images/{rentalId}")
    public ResponseEntity<byte[]> getImageByRentalId(@PathVariable("rentalId") Integer rentalId) {
        
        ImageDto imageDto = rentalService.getImageRentalById(rentalId);
       
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(imageDto.getImageType()))
                .body(imageDto.getImageData());
    }
    

    @Operation(
        summary = "Update a rental",
        description = "Update a rental selected by ID by providing the information to update",
        responses = {
            @ApiResponse(responseCode = "200", description = "Rental updated"),
            @ApiResponse(
                responseCode = "401", 
                description = "Invalid credentials",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
            @ApiResponse(
                responseCode = "404", 
                description = "Rental not found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
                )),
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
    public ResponseEntity<RentalUpdateResponseDto> updateRental(
        @PathVariable int rentalId, 
        @RequestParam("name") String name,
        @RequestParam("surface") BigDecimal surface,
        @RequestParam("price") BigDecimal price,
        @RequestParam("description") String description) {

        RentalUpdateRequestDto rentalUpdatedDto = new RentalUpdateRequestDto(name, surface, price, description);
       
        rentalService.updateRental(rentalId, rentalUpdatedDto);

        return new ResponseEntity<RentalUpdateResponseDto>(new RentalUpdateResponseDto("Rental updated"), HttpStatus.OK);
    }
}
