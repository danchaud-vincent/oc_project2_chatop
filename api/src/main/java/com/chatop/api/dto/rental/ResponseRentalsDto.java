package com.chatop.api.dto.rental;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRentalsDto {

    public List<RentalDto> rentals;

}
