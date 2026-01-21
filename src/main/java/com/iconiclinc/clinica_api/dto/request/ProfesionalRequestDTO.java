package com.iconiclinc.clinica_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ProfesionalRequestDTO {
    @NotBlank(message = "Name is required")
    private String nombre;
    @NotBlank(message = "Profession is required")
    private String profesion;

    public String getNombre() {
        return nombre;
    }

    public String getProfesion() {
        return profesion;
    }
}
