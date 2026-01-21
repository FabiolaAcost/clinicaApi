package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.response.PacienteResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Rutina;
import com.iconiclinc.clinica_api.exception.PatientNotFoundException;
import com.iconiclinc.clinica_api.mapper.PacienteMapper;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.RutinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private static final Logger log = LoggerFactory.getLogger(PacienteServiceImpl.class);
    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteServiceImpl(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    @Override
    public Paciente getPacienteByRut(String rut) {
        log.info("Searching for patient with rut {}", rut);

        return pacienteRepository.findByRut(rut)
                .orElseThrow(()->{
                    log.warn("Patient not found with RUT {}", rut);
                    return new PatientNotFoundException("Patient not found with RUT: " + rut);
                });
    }

    @Override
    public PacienteResponseDTO getPacienteById(Integer id) {
        log.info("Attempting to fetch patient with ID {}", id);

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(()->{
                    log.error("Patient not found with ID {}", id);
                    return new PatientNotFoundException(id);
                });
        return pacienteMapper.toResponseDTO(paciente);
    }
}
