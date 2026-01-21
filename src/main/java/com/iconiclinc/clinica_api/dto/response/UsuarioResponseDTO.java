package com.iconiclinc.clinica_api.dto.response;

import jakarta.validation.constraints.NotBlank;
public class UsuarioResponseDTO {
    private String rut;
    private String rol;


    public UsuarioResponseDTO(String rut, String rol) {
        this.rut = rut;
        this.rol = rol;
    }

    public String getRut() {
        return rut;
    }

    public String getRol() {
        return rol;
    }
}
