package com.chatop.api.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {

    @NotBlank(message = "Email mandatory")
    @Schema(description = "Email of the user", example = "user@example.com")
    private String email;

    @NotBlank(message = "Password mandatory")
    @Schema(description = "Password of the user")
    private String password;

}
