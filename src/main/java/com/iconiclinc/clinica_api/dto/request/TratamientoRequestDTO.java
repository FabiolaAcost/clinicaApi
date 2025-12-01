package com.iconiclinc.clinica_api.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class TratamientoRequestDTO {
    @NotBlank(message = "Treatment type is required.")
    private String tipo;
    @NotBlank(message = "Date is required.")
    private LocalDate fecha;

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
