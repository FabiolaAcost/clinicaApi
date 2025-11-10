package com.iconiclinc.clinica_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer id;

    @NotBlank(message = "Name is required")
    private String nombre;

    @NotBlank(message = "Rut is required")
    @Pattern(regexp = "\\d{7,8}-[\\dkK]", message = "Invalid RUT format")
    @Column(unique = true, nullable = false)
    private String rut;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = true, unique = true)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_profesional", nullable = false)
    private Profesional profesional;

    public Paciente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }
}
