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
    public PacienteResponseDTO addPatient(Integer professionalId, PacienteRequestDTO requestDTO) {
        log.info("Attempting to add new patient with RUT {}", requestDTO.getRut());

        Profesional profesional = profesionalRepository.findById(professionalId)
                .orElseThrow(() -> new ProfessionalNotFoundException(professionalId));

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
    public List<PacienteResponseDTO> getPatientsByProfessional(Integer professionalId) {
        log.info("Fetching all patients for professional ID: {}", professionalId);

        profesionalRepository.findById(professionalId)
                .orElseThrow( () ->{
                    log.error("Professional not found with ID {}", professionalId);
                    return new ProfessionalNotFoundException(professionalId);
                });


        List<Paciente> pacientes =
                pacienteRepository.findByProfesionalId(professionalId);

        if (pacientes.isEmpty()) {
            log.info("No patients found for professional ID {}", professionalId);
        } else {
            log.info("Found {} patients for professional ID {}", pacientes.size(), professionalId);
        }

        return pacientes.stream()
                .map(pacienteMapper::toResponseDTO)
                .toList();
    }
}
