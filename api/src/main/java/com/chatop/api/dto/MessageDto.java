package com.chatop.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objet représentant un Message")
public class MessageDto {

    @Schema(description = "Identifiant de l'utilisateur")
    @JsonProperty("user_id")
    private Integer userId;

    @Schema(description = "Identifiant de la location")
    @JsonProperty("rental_id")
    private Integer rentalId;

    private String message;

}
