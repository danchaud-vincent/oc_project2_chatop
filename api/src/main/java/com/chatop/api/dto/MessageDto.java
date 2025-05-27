package com.chatop.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("rental_id")
    private Integer rentalId;

    private String message;

}
