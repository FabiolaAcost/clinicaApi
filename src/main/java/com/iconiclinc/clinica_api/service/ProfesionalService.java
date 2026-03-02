package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.PacienteRequestDTO;
import com.iconiclinc.clinica_api.dto.response.PacienteResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;

import java.util.List;

public interface ProfesionalService {
    PacienteResponseDTO addPatientByEmail(String email, PacienteRequestDTO requestDTO);

    List<PacienteResponseDTO> getPatientsByEmail(String email);
}
