package com.iconiclinc.clinica_api.dto.response;

public class ProfesionalResponseDTO {
    private String nombre;
    private String profesion;

    public ProfesionalResponseDTO(String nombre, String profesion) {
        this.nombre = nombre;
        this.profesion = profesion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesion() {
        return profesion;
    }
}
