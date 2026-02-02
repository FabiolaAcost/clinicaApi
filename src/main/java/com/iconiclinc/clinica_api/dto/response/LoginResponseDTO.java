package com.iconiclinc.clinica_api.dto.response;

public record LoginResponseDTO(
        String token,
        String tokenType,
        UsuarioResponseDTO user
) { }
