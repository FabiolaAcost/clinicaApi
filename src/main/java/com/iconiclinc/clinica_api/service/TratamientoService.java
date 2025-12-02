package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.TratamientoRequestDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoResponseDTO;
import com.iconiclinc.clinica_api.entity.Tratamiento;

import java.util.List;

public interface TratamientoService {
    TratamientoResponseDTO addTreatment(Integer pacienteId, Integer profesionalId, TratamientoRequestDTO tratamiento);

    TratamientoListResponseDTO getTreatmentsByPatient(Integer pacienteId);
}
