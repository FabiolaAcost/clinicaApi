package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.RutinaRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.entity.Rutina;
import com.iconiclinc.clinica_api.entity.TipoRutina;
import com.iconiclinc.clinica_api.exception.BusinessException;
import com.iconiclinc.clinica_api.exception.PatientNotFoundException;
import com.iconiclinc.clinica_api.exception.ProfessionalNotFoundException;
import com.iconiclinc.clinica_api.exception.RoutineNotFoundException;
import com.iconiclinc.clinica_api.mapper.RutinaMapper;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.ProfesionalRepository;
import com.iconiclinc.clinica_api.repository.RutinaRepository;
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
    public RutinaResponseDTO addRoutine(Integer pacienteId, Integer profesionalId, RutinaRequestDTO requestDTO) {
        log.info("Attempting to add new routine for patient ID: {} by professional ID: {}",
                pacienteId, profesionalId);
        Paciente patient = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PatientNotFoundException(pacienteId));

        Profesional professional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new ProfessionalNotFoundException(profesionalId));

        TipoRutina tipo;
        try {
            tipo = TipoRutina.valueOf(requestDTO.getTipo().toUpperCase());
        } catch (IllegalArgumentException ex) {
            log.warn("Invalid routine type: {}", requestDTO.getTipo());
            throw new BusinessException("Invalid routine type");
        }

        Rutina rutina = rutinaMapper.toEntity(tipo, requestDTO.getDescripcion(), patient, professional);
        Rutina saved = rutinaRepository.save(rutina);
        log.info("Routine saved successfully for patient ID: {} by professional ID: {}  with type: {}", pacienteId, profesionalId, requestDTO.getTipo());
        return rutinaMapper.toResponseDTO(saved);
    }

    @Override
    public List<RutinaResponseDTO> getRoutinesByPatient(Integer pacienteId) {
        log.info("Fetching all routines for patient ID: {}", pacienteId);

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID {}", pacienteId);
                    return new PatientNotFoundException(pacienteId);
                });

        List<Rutina> rutinas = rutinaRepository.findByPacienteId(pacienteId);

        if (rutinas.isEmpty()) {
            log.warn("No routines found for patient ID: {}", pacienteId);
        } else {
            log.info("Found {} routines for patient ID {}", rutinas.size(), pacienteId);
        }

        return rutinaMapper.toResponseDTOList(rutinas);
    }

    @Override
    public RutinaResponseDTO updateRoutine(Integer rutinaId, RutinaRequestDTO requestDTO) {
        log.info("Updating treatment type for routine ID {}", rutinaId);
        Rutina rutina = rutinaRepository.findById(rutinaId)
                .orElseThrow(() ->{
                    log.error("Routine not found with id {}", rutinaId);
                    return new RoutineNotFoundException(rutinaId);
                });

        rutina.setDescripcion(requestDTO.getDescripcion());

        Rutina updated = rutinaRepository.save(rutina);
        log.info("Routine ID {} updated successfully", rutinaId);
        return rutinaMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteRoutine(Integer rutinaId) {
        log.info("Deleting routine with ID {}", rutinaId);
        Rutina rutina = rutinaRepository.findById(rutinaId)
                .orElseThrow(()->{
                    log.error("Routine not found with ID {}", rutinaId);
                    return new RoutineNotFoundException(rutinaId);
                });

        rutinaRepository.delete(rutina);
        log.info("Routine ID {} deleted successfully", rutinaId);
    }
}
