package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.RutinaRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaResponseDTO;

import java.util.List;

public interface RutinaService{
    RutinaResponseDTO addRoutine(Integer pacienteId, Integer profesionalId, RutinaRequestDTO requestDTO);
    List<RutinaResponseDTO> getRoutinesByPatient(Integer pacienteId);
    RutinaResponseDTO updateRoutine(Integer rutinaId, RutinaRequestDTO requestDTO);
    void deleteRoutine(Integer rutinaId);
}
