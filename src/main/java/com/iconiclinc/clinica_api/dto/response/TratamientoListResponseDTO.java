package com.iconiclinc.clinica_api.dto.response;

import java.util.List;

public class TratamientoListResponseDTO {
    private List<TratamientoResponseDTO> tratamientoResponseDTOS;

    private String message;

    public TratamientoListResponseDTO(List<TratamientoResponseDTO> tratamientoResponseDTOS, String message) {
        this.tratamientoResponseDTOS = tratamientoResponseDTOS;
        this.message = message;
    }

    public List<TratamientoResponseDTO> getTratamientoResponseDTOS() {
        return tratamientoResponseDTOS;
    }

    public String getMessage() {
        return message;
    }
}
