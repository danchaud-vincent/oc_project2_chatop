package com.chatop.api.dto.rental;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCreateRequestDto {

    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String description;
    private Integer ownerId;
    
}
