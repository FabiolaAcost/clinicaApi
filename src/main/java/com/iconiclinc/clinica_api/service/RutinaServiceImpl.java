package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.RutinaRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.entity.Rutina;
import com.iconiclinc.clinica_api.entity.TipoRutina;
import com.iconiclinc.clinica_api.mapper.RutinaMapper;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.ProfesionalRepository;
import com.iconiclinc.clinica_api.repository.RutinaRepository;
import org.apache.catalina.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutinaServiceImpl implements RutinaService{
    private static final Logger log = LoggerFactory.getLogger(RutinaServiceImpl.class);

    private final RutinaRepository rutinaRepository;
    private  final PacienteRepository pacienteRepository;
    private  final ProfesionalRepository profesionalRepository;
    private final RutinaMapper rutinaMapper;

    public RutinaServiceImpl(RutinaRepository rutinaRepository, PacienteRepository pacienteRepository, ProfesionalRepository profesionalRepository, RutinaMapper rutinaMapper) {
        this.rutinaRepository = rutinaRepository;
        this.pacienteRepository = pacienteRepository;
        this.profesionalRepository = profesionalRepository;
        this.rutinaMapper = rutinaMapper;
    }

    @Override
    public RutinaResponseDTO addRoutine(Integer pacienteId, Integer profesionalId, RutinaRequestDTO routine) {
        log.info("Attempting to add new routine for patient ID: {} by professional ID: {}", pacienteId, profesionalId);
        Paciente patient = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID:" + pacienteId));

        Profesional professional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new RuntimeException("Professional not found with ID: " + profesionalId));

        Rutina rutina = rutinaMapper.toEntity(routine, patient,professional);
        Rutina saved = rutinaRepository.save(rutina);
        log.info("Routine saved successfully for patient ID: {} by professional ID: {}  with type: {}", pacienteId, profesionalId, routine.getTipo());
        return rutinaMapper.toResponseDTO(saved);
    }

    @Override
    public RutinaListResponseDTO getRoutinesByPatient(Integer pacienteId) {
        log.info("Fetching all routines for patient ID: {}", pacienteId);

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID {}", pacienteId);
                    return new RuntimeException("Patient not found with ID: " + pacienteId);
                });

        List<Rutina> rutinas = rutinaRepository.findByPaciente_id(pacienteId);

        if (rutinas.isEmpty()) {
            log.warn("No routines found for patient ID: {}", pacienteId);
        } else {
            log.info("Found {} routines for patient ID {}", rutinas.size(), pacienteId);
        }

        List<RutinaResponseDTO> responseList = rutinaMapper.toResponseDTOList(rutinas);
        String message = rutinas.isEmpty()
                ? "No hay rutinas registradas para este paciente."
                : "Rutinas obtenidas exitosamente";

        log.info("Returning routine list response for patient ID {} with {} routines.",
                pacienteId, rutinas.size());
        return new RutinaListResponseDTO(responseList, message);
    }
}
