package com.iconiclinc.clinica_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rutina")
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rutina")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoRutina tipo;

    @NotBlank(message = "Description is required")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    @JsonIgnoreProperties({"profesional", "usuario"})
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_profesional", nullable = false)
    @JsonIgnoreProperties({"usuario"})
    private Profesional profesional;

    public Rutina() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoRutina getTipo() {
        return tipo;
    }

    public void setTipo(TipoRutina tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }
}
