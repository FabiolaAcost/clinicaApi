package com.iconiclinc.clinica_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    private String contrasena;

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }
}
