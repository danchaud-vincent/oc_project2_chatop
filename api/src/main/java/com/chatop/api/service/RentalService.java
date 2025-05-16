package com.chatop.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;

@Service
public class RentalService {

    @Autowired
    RentalRepository rentalRepository;

    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public Rental addRental(Rental rental) {

        

        return rentalRepository.save(rental);
    }

}
