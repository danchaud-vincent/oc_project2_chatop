package com.chatop.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {

    private Integer id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String description;
    private String picture;

    @JsonProperty("owner_id")
    private Integer ownerId;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedat;

}
