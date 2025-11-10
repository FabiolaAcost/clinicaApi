package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.entity.Paciente;

import java.util.Optional;

public interface PacienteService {
    Optional<Paciente> findByRut(String rut);
}
