package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.entity.Paciente;

import java.util.List;

public interface ProfesionalService {
    Paciente addPatient(Paciente paciente, Integer id);

    List<Paciente> getPatientsByProfessional(Integer id);
}
