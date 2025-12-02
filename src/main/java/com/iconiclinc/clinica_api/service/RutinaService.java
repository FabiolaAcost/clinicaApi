package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.RutinaRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaResponseDTO;
import com.iconiclinc.clinica_api.entity.Rutina;

import java.util.List;

public interface RutinaService {
    RutinaResponseDTO addRoutine(Integer pacienteId, Integer profesionalId, RutinaRequestDTO routine);
    RutinaListResponseDTO getRoutinesByPatient(Integer pacienteId);

}
