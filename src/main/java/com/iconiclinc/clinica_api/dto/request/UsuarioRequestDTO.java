package com.iconiclinc.clinica_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UsuarioRequestDTO {
    @NotBlank(message = "Rut is required")
    @Pattern(regexp = "\\d{7,8}-[\\dkK]", message = "Invalid RUT format")
    private String rut;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String contrasena;

    private String rol;

    public String getRut() {
        return rut;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }
}
