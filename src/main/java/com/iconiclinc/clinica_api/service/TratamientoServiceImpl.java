package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.TratamientoRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.entity.Tratamiento;
import com.iconiclinc.clinica_api.mapper.TratamientoMapper;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.ProfesionalRepository;
import com.iconiclinc.clinica_api.repository.TratamientoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.*;
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
    public TratamientoResponseDTO addTreatment(Integer pacienteId, Integer profesionalId, TratamientoRequestDTO treatment) {
        log.info("Attempting to add new treatment for patient ID {} by professional ID {}", pacienteId, profesionalId);

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID {}", pacienteId);
                    return new RuntimeException("Patient not found with ID: " + pacienteId);
                });

        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> {
                    log.error("Professional not found with ID {}", profesionalId);
                    return new RuntimeException("Professional not found with ID: " + profesionalId);
                });

        Tratamiento tratamiento = tratamientoMapper.toEntity(treatment, paciente, profesional);
        Tratamiento saved = tratamientoRepository.save(tratamiento);
        log.info("Treatment saved successfully with ID {} for patient {} by professional {}",
                saved.getId(), pacienteId, profesionalId);
        return tratamientoMapper.toResponseDTO(saved);
    }

    @Override
    public TratamientoListResponseDTO getTreatmentsByPatient(Integer pacienteId) {
        log.info("Fetching all treatments for patient ID: {}", pacienteId);

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID {}", pacienteId);
                    return new RuntimeException("Patient not found with ID: " + pacienteId);
                });

        List<Tratamiento> treatments = tratamientoRepository.findByPaciente_id(pacienteId);
        if (treatments.isEmpty()){
            log.warn("No treatments found for patient ID: {}", pacienteId);
        } else {
            log.info("Found {} treatments for patient ID {}", treatments.size(), pacienteId);
        }

        List<TratamientoResponseDTO> responseList = tratamientoMapper.toResponseDTOlist(treatments);
        String message = treatments.isEmpty()
                ? "No hay tratamientos registrados para este paciente."
                : "Tratamientos obtenidos exitosamente";

        log.info("Returning treatment list response for patient ID {} with {} treatments.",
                pacienteId, treatments.size());
        return new TratamientoListResponseDTO(responseList, message);
    }
}
