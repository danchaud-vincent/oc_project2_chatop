package com.chatop.api.dto.rental;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objet représentant une location")
public class RentalDto {

    @Schema(description = "Identifiant unique de la location")
    private Integer id;

    @Schema(description = "Nom de la location")
    private String name;

    @Schema(description = "Surface en mètres carrés de la location")
    private BigDecimal surface;

    @Schema(description = "Prix de la location")
    private BigDecimal price;

    @Schema(description = "Description supplémentaire sur la location")
    private String description;

    @Schema(description = "image représentant la location")
    private String picture;

    @Schema(description = "image représentant la location")
    private String pictureName;

    @Schema(description = "image représentant la location")
    private String pictureType;

    @Schema(description = "image représentant la location")
    private byte[] pictureData;

    @Schema(description = "Identifiant unique de l'utilisateur")
    @JsonProperty("owner_id")
    private Integer ownerId;

    @Schema(description = "Date de création de l'annonce")
    @JsonProperty("created_at")
    private Date createdAt;

    @Schema(description = "Date de mise à jour d'une information sur la location")
    @JsonProperty("updated_at")
    private Date updatedat;

}
