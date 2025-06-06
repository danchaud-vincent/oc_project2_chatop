package com.chatop.api.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Message object")
public class MessageRequestDto {

    @Schema(description = "Owner ID")
    @NotNull(message = "user_id mandatory")
    @JsonProperty("user_id")
    private Integer userId;

    @Schema(description = "Rental ID")
    @NotNull(message = "rental_id mandatory")
    @JsonProperty("rental_id")
    private Integer rentalId;

    @Schema(description = "Message sent to the owner of the rental")
    @NotBlank(message = "Message mandatory")
    private String message;

}
