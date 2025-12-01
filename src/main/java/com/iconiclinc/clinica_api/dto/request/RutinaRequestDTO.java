package com.iconiclinc.clinica_api.dto.request;

import com.iconiclinc.clinica_api.entity.TipoRutina;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public class RutinaRequestDTO {

    @NotBlank(message = "Routine type is required.")
    private String tipo;
    @NotBlank(message = "Description is required")
    private String descripcion;

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
