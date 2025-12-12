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

    private static final Logger log = LoggerFactory.getLogger(ProfesionalServiceImpl.class);
    private final PacienteRepository pacienteRepository;
    private final RutinaRepository rutinaRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteServiceImpl(PacienteRepository pacienteRepository, RutinaRepository rutinaRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.rutinaRepository = rutinaRepository;
        this.pacienteMapper = pacienteMapper;
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
