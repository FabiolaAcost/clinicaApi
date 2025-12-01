package com.iconiclinc.clinica_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RecomendacionRequestDTO {
    @NotBlank(message = "Description is required")
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }
}
