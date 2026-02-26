package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.TratamientoRequestDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoResponseDTO;

import java.util.List;

public interface TratamientoService {
    TratamientoResponseDTO addTreatment(Integer pacienteId, String email, TratamientoRequestDTO requestDTO);

    List<TratamientoResponseDTO> getTreatmentsByPatient(Integer pacienteId);

    TratamientoResponseDTO updateTreatment(Integer tratamientoId, TratamientoRequestDTO requestDTO);

    void deleteTreatment(Integer tratamientoId);
}
