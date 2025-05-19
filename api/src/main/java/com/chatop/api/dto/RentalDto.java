package com.chatop.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {

    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String description;
    private String picture;
    private int ownerId;
    private Date createdAt;
    private Date updatedat;

}
