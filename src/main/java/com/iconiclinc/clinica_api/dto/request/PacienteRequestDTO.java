package com.iconiclinc.clinica_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PacienteRequestDTO {
    @NotBlank(message = "Name is required")
    private String nombre;
    @NotBlank(message = "Rut is required")
    private String rut;

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }
}
