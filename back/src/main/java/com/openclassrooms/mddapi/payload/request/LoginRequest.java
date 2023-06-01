package com.openclassrooms.mddapi.payload.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Credential for login user
 */
@Data
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
