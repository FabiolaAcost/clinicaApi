package com.iconiclinc.clinica_api.dto.response;

import java.util.List;

public class RecomendacionListResponseDTO {
    private List<RecomendacionResponseDTO> recomendacionResponseDTOS;

    private String message;

    public RecomendacionListResponseDTO(List<RecomendacionResponseDTO> recomendacionResponseDTOS, String message) {
        this.recomendacionResponseDTOS = recomendacionResponseDTOS;
        this.message = message;
    }

    public List<RecomendacionResponseDTO> getRecomendacionResponseDTOS() {
        return recomendacionResponseDTOS;
    }

    public String getMessage() {
        return message;
    }
}
