package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.response.PacienteResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Rutina;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    Optional<Paciente> findByRut(String rut);
    PacienteResponseDTO getPacienteById(Integer id);
}
