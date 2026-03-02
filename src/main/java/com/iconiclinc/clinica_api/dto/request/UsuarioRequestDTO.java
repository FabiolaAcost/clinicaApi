package com.iconiclinc.clinica_api.dto.request;

import com.iconiclinc.clinica_api.entity.TipoRol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UsuarioRequestDTO {
    @NotBlank(message = "Rut is required")
    @Pattern(regexp = "\\d{7,8}-[\\dkK]", message = "Invalid RUT format")
    private String rut;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String contrasena;
    @NotNull(message = "Role is required")
    private TipoRol rol;
    private String nombre;
    private String profesion;

    public String getRut() {
        return rut;
    }
    public String getEmail() {
        return email;
    }
    public String getContrasena() {
        return contrasena;
    }

    public TipoRol getRol() {
        return rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesion() {
        return profesion;
    }
}
