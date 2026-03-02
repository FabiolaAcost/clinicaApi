package com.iconiclinc.clinica_api.dto.response;

public class RutinaResponseDTO {
    private Integer id;

    private String tipo;

    private String descripcion;

    private String profesional;

    public RutinaResponseDTO() {
    }

    public RutinaResponseDTO(Integer id, String tipo, String descripcion, String profesional) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.profesional = profesional;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProfesionalNombre() {
        return profesional;
    }

    public void setProfesionalNombre(String profesional) {
        this.profesional = profesional;
    }
}
