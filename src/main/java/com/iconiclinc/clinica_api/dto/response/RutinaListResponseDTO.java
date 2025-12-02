package com.iconiclinc.clinica_api.dto.response;

import java.io.Serializable;
import java.util.List;

public class RutinaListResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<RutinaResponseDTO> rutinaResponseDTOS;

    private String message;

    public RutinaListResponseDTO(List<RutinaResponseDTO> rutinaResponseDTOS, String message) {
        this.rutinaResponseDTOS = rutinaResponseDTOS;
        this.message = message;
    }

    public List<RutinaResponseDTO> getRutinaResponseDTOS() {
        return rutinaResponseDTOS;
    }

    public void setRutinaResponseDTOS(List<RutinaResponseDTO> rutinaResponseDTOS) {
        this.rutinaResponseDTOS = rutinaResponseDTOS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
