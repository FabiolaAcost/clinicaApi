package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.PacienteRequestDTO;
import com.iconiclinc.clinica_api.dto.response.PacienteResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;

import java.util.List;

public interface ProfesionalService {
    PacienteResponseDTO addPatient(Integer professionalId, PacienteRequestDTO requestDTO);

    List<PacienteResponseDTO> getPatientsByProfessional(Integer professionalId);
}
