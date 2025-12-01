package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Rutina;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    Optional<Paciente> findByRut(String rut);
    Paciente getPacienteById(Integer id);
}
