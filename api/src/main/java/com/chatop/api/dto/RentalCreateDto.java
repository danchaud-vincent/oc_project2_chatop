package com.chatop.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCreateDto {

    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String description;
    private String picture;
    
}
