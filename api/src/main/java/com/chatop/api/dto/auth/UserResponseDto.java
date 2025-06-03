package com.chatop.api.dto.auth;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "user Object")
public class UserResponseDto {

    @Schema(description = "Unique User ID")
    private Integer id;
   
    @Schema(description = "User name")
    private String name;

    @Schema(description = "User email")
    private String email;

    @Schema(description = "Creation date of the user")
    @JsonProperty("created_at")
    private Date createdAt;

    @Schema(description = "Update date of the user information")
    @JsonProperty("updated_at")
    private Date updatedAt;

}
