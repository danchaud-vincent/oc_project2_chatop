package com.chatop.api.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "Name mandatory")
    private String name;

    @NotBlank(message = "Email mandatory")
    private String email;

    @NotBlank(message = "Password mandatory")
    private String password;

}
