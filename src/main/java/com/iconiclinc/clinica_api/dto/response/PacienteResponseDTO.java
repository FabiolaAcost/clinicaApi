package com.iconiclinc.clinica_api.dto.response;

public class PacienteResponseDTO {
    private Integer id;

    private String nombre;
    private String rut;

    public PacienteResponseDTO(Integer id, String nombre, String rut) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }
}
