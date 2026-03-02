package com.iconiclinc.clinica_api.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TratamientoRequestDTO {
    @NotBlank(message = "Treatment type is required.")
    private String tipo;
    @NotNull(message = "Date is required.")
    @FutureOrPresent(message = "Date must be today or future.")
    private LocalDate fecha;

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
