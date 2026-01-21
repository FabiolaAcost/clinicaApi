package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.TratamientoRequestDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.entity.TipoTratamiento;
import com.iconiclinc.clinica_api.entity.Tratamiento;
import com.iconiclinc.clinica_api.exception.BusinessException;
import com.iconiclinc.clinica_api.exception.PatientNotFoundException;
import com.iconiclinc.clinica_api.exception.ProfessionalNotFoundException;
import com.iconiclinc.clinica_api.exception.TreatmentNotFoundException;
import com.iconiclinc.clinica_api.mapper.TratamientoMapper;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.ProfesionalRepository;
import com.iconiclinc.clinica_api.repository.TratamientoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TratamientoServiceImpl implements TratamientoService{

    private static final Logger log = LoggerFactory.getLogger(TratamientoServiceImpl.class);
    private final TratamientoRepository tratamientoRepository;
    private final PacienteRepository pacienteRepository;

    private final ProfesionalRepository profesionalRepository;
    private final TratamientoMapper tratamientoMapper;

    public TratamientoServiceImpl(TratamientoRepository tratamientoRepository, PacienteRepository pacienteRepository, ProfesionalRepository profesionalRepository, TratamientoMapper tratamientoMapper) {
        this.tratamientoRepository = tratamientoRepository;
        this.pacienteRepository = pacienteRepository;
        this.profesionalRepository = profesionalRepository;
        this.tratamientoMapper = tratamientoMapper;
    }

    @Override
    public TratamientoResponseDTO addTreatment(Integer pacienteId, Integer profesionalId, TratamientoRequestDTO requestDTO) {
        log.info("Adding treatment for patient {} by professional {}", pacienteId, profesionalId);

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID {}", pacienteId);
                    return new PatientNotFoundException(pacienteId);
                });

        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> {
                    log.error("Professional not found with ID {}", profesionalId);
                    return new ProfessionalNotFoundException(profesionalId);
                });

        TipoTratamiento tipo;
        try {
            tipo = TipoTratamiento.valueOf(requestDTO.getTipo().toUpperCase());
        } catch (IllegalArgumentException ex) {
            log.warn("Invalid treatment type: {}", requestDTO.getTipo());
            throw new BusinessException("Invalid treatment type");
        }

        Tratamiento tratamiento = tratamientoMapper.toEntity(tipo,
                requestDTO.getFecha(),paciente, profesional);

        Tratamiento saved = tratamientoRepository.save(tratamiento);

        log.info("Treatment saved successfully with ID {} for patient {} by professional {}",
                saved.getId(), pacienteId, profesionalId);

        return tratamientoMapper.toResponseDTO(saved);
    }

    @Override
    public List<TratamientoResponseDTO> getTreatmentsByPatient(Integer pacienteId) {
        log.info("Fetching all treatments for patient ID: {}", pacienteId);

       pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID {}", pacienteId);
                    return new PatientNotFoundException(pacienteId);
                });

        List<Tratamiento> treatments = tratamientoRepository.findByPacienteId(pacienteId);
        if (treatments.isEmpty()){
            log.warn("No treatments found for patient ID: {}", pacienteId);
        } else {
            log.info("Found {} treatments for patient ID {}", treatments.size(), pacienteId);
        }

        return tratamientoMapper.toResponseDTOlist(treatments);
    }

    @Override
    public TratamientoResponseDTO updateTreatment(Integer tratamientoId, TratamientoRequestDTO requestDTO) {
            log.info("Updating treatment type for treatment ID {}", tratamientoId);

        Tratamiento tratamiento = tratamientoRepository.findById(tratamientoId)
                .orElseThrow(() -> {
                    log.error("Treatment not found with id {}", tratamientoId);
                    return new TreatmentNotFoundException(tratamientoId);
                });

        TipoTratamiento tipo;
        try {
            tipo = TipoTratamiento.valueOf(requestDTO.getTipo().toUpperCase());
        } catch (IllegalArgumentException ex){
            log.warn("Invalid treatment type: {}", requestDTO.getTipo());
            throw new BusinessException("Invalid treatment type");
        }

        tratamiento.setTipo(tipo);
        Tratamiento updated = tratamientoRepository.save(tratamiento);
        log.info("Treatment ID {} updated successfully", tratamientoId);
        return tratamientoMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteTreatment(Integer tratamientoId) {
        log.info("Deleting treatment with ID {}", tratamientoId);

        Tratamiento tratamiento = tratamientoRepository.findById(tratamientoId)
                .orElseThrow(() -> {
                    log.error("Treatment not found with ID {}", tratamientoId);
                    return new TreatmentNotFoundException(tratamientoId);
                });

        tratamientoRepository.delete(tratamiento);

        log.info("Treatment ID {} deleted successfully", tratamientoId);
    }
}
