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
@Schema(description = "Rental object")
public class RentalDto {

    @Schema(description = "Unique rental ID")
    private Integer id;

    @Schema(description = "Rental name")
    private String name;

    @Schema(description = "Square metres of rental space")
    private BigDecimal surface;

    @Schema(description = "Rental price")
    private BigDecimal price;

    @Schema(description = "Rental description")
    private String description;

    @Schema(description = "Rental image url")
    private String picture;

    @Schema(description = "Rental image name")
    private String pictureName;

    @Schema(description = "Rental image type")
    private String pictureType;

    @Schema(description = "Owner ID")
    @JsonProperty("owner_id")
    private Integer ownerId;

    @Schema(description = "Date of creation")
    @JsonProperty("created_at")
    private Date createdAt;

    @Schema(description = "Update date of rental information")
    @JsonProperty("updated_at")
    private Date updatedat;

}
