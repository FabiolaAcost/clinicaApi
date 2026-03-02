package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.PacienteRequestDTO;
import com.iconiclinc.clinica_api.dto.response.PacienteResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.exception.BusinessException;
import com.iconiclinc.clinica_api.exception.ProfessionalNotFoundException;
import com.iconiclinc.clinica_api.mapper.PacienteMapper;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.ProfesionalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalServiceImpl implements ProfesionalService{

    private static final Logger log = LoggerFactory.getLogger(ProfesionalServiceImpl.class);
    private final PacienteRepository pacienteRepository;
    private final ProfesionalRepository profesionalRepository;
    private final PacienteMapper pacienteMapper;

    public ProfesionalServiceImpl(PacienteRepository pacienteRepository, ProfesionalRepository profesionalRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.profesionalRepository = profesionalRepository;
        this.pacienteMapper = pacienteMapper;
    }

    @Override
    public PacienteResponseDTO addPatientByEmail(String email, PacienteRequestDTO requestDTO) {
        log.info("Attempting to add new patient with RUT {} for professional {}",
                requestDTO.getRut(), email);

        Profesional profesional = profesionalRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> {
                    log.error("Professional not found with email {}", email);
                    return new ProfessionalNotFoundException(email);
                });

        if (pacienteRepository.existsByRut(requestDTO.getRut())){
            log.warn("Patient with RUT {} already exists", requestDTO.getRut());
            throw new BusinessException("Patient with this rut already exists");
        }

        Paciente paciente = pacienteMapper.toEntity(requestDTO);
        paciente.setProfesional(profesional);

        Paciente saved = pacienteRepository.save(paciente);
        log.info("New patient saved successfully with ID: {}", saved.getId());
        return pacienteMapper.toResponseDTO(saved);
    }

    @Override
    public List<PacienteResponseDTO> getPatientsByEmail(String email) {
        log.info("Fetching all patients for professional email: {}", email);

        Profesional profesional = profesionalRepository.findByUsuarioEmail(email)
                .orElseThrow( () ->{
                    return new ProfessionalNotFoundException(email);
                });

        List<Paciente> pacientes =
                pacienteRepository.findByProfesional(profesional);

        if (pacientes.isEmpty()) {
            log.info("No patients found for professional email {}", email);
        } else {
            log.info("Found {} patients for professional email {}", pacientes.size(), email);
        }

        return pacientes.stream()
                .map(pacienteMapper::toResponseDTO)
                .toList();
    }
}
