package com.iconiclinc.clinica_api.dto.response;

import java.time.LocalDate;

public class TratamientoResponseDTO {
    private Integer id;

    private String tipo;

    private LocalDate fecha;

    private String paciente;

    private String profesional;

    public TratamientoResponseDTO(Integer id, String tipo, LocalDate fecha, String paciente, String profesional) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.paciente = paciente;
        this.profesional = profesional;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getPacienteNombre() {
        return paciente;
    }

    public String getProfesionalNombre() {
        return profesional;
    }
}
