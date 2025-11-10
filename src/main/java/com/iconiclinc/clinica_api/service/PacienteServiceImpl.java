package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private static final Logger log = LoggerFactory.getLogger(ProfesionalServiceImpl.class);
    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Optional<Paciente> findByRut(String rut) {
        log.info("Searching for patient with rut {}", rut);
        Optional<Paciente> paciente = pacienteRepository.findByRut(rut);
        if (paciente.isPresent()){
            log.info("Patient found with RUT {}", rut);
        } else {
            log.warn("No patient found with rut {}", rut);
        }
        return paciente;
    }
}
