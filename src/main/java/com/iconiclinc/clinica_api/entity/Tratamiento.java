package com.iconiclinc.clinica_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "tratamientos")
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamiento")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTratamiento tipo;

    @NotBlank(message = "Date is required")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    @JsonIgnoreProperties({"profesional", "usuario"})
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_profesional", nullable = false)
    @JsonIgnoreProperties({"usuario"})
    private Profesional profesional;

    public Tratamiento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoTratamiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoTratamiento tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
