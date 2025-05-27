package com.chatop.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objet représentant un Utilisateur")
public class UserDto {

    @Schema(description = "Identifiant unique de l'utilisateur")
    private Integer id;
   
    @Schema(description = "Nom complet de l'utilisateur")
    private String name;

    @Schema(description = "email de l'utilisateur")
    private String email;

    @Schema(description = "date de création de l'utilisateur")
    @JsonProperty("created_at")
    private Date createdAt;

    @Schema(description = "date d'une mise à jour d'information")
    @JsonProperty("updated_at")
    private Date updatedAt;

}
