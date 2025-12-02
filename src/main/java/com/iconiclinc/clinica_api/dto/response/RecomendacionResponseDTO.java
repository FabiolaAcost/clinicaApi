package com.iconiclinc.clinica_api.dto.response;

public class RecomendacionResponseDTO {
    private Integer id;

    private String descripcion;

    private String paciente;

    private String profesional;

    public RecomendacionResponseDTO(Integer id, String descripcion, String paciente, String profesional) {
        this.id = id;
        this.descripcion = descripcion;
        this.paciente = paciente;
        this.profesional = profesional;
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getProfesional() {
        return profesional;
    }
}