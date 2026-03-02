package com.iconiclinc.clinica_api.dto.response;


import java.util.List;

public class UsuarioListResponseDTO {
    private List<UsuarioResponseDTO> usuarioResponseDTOS;

    private String message;

    public UsuarioListResponseDTO(List<UsuarioResponseDTO> usuarioResponseDTOS, String message) {
        this.usuarioResponseDTOS = usuarioResponseDTOS;
        this.message = message;
    }

    public List<UsuarioResponseDTO> getUsuarioResponseDTOS(){
        return usuarioResponseDTOS;
    }

    public String getMessage() {
        return message;
    }
}
