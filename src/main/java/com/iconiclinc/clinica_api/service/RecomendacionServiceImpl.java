package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.RecomendacionRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RecomendacionListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.RecomendacionResponseDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.entity.Recomendacion;
import com.iconiclinc.clinica_api.entity.Tratamiento;
import com.iconiclinc.clinica_api.mapper.RecomendacionMapper;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.ProfesionalRepository;
import com.iconiclinc.clinica_api.repository.RecomendacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacionServiceImpl implements RecomendacionService{

    private static final Logger log = LoggerFactory.getLogger(RecomendacionServiceImpl.class);

    private final RecomendacionRepository recomendacionRepository;

    private final PacienteRepository pacienteRepository;

    private final ProfesionalRepository profesionalRepository;
    private final RecomendacionMapper recomendacionMapper;

    public RecomendacionServiceImpl(RecomendacionRepository recomendacionRepository, PacienteRepository pacienteRepository, ProfesionalRepository profesionalRepository, RecomendacionMapper recomendacionMapper) {
        this.recomendacionRepository = recomendacionRepository;
        this.pacienteRepository = pacienteRepository;
        this.profesionalRepository = profesionalRepository;
        this.recomendacionMapper = recomendacionMapper;
    }

    @Override
    public RecomendacionResponseDTO addRecommendation(Integer pacienteId, Integer profesionalId, RecomendacionRequestDTO recomendacion) {
        log.info("Attempting to add new recommendation for patient ID {} by professional ID {}", pacienteId, profesionalId);

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

        Recomendacion recomend = recomendacionMapper.toEntity(recomendacion, paciente, profesional);
        Recomendacion saved = recomendacionRepository.save(recomend);
        log.info("Recommendation saved successfully with ID {} for patient {} by professional {}",
                saved.getId(), pacienteId, profesionalId);
        return recomendacionMapper.toResponseDTO(saved);
    }

    @Override
    public RecomendacionListResponseDTO getRecommendationsByPatient(Integer pacienteId) {
        log.info("Fetching all recommendations for patient ID: {}", pacienteId);

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID {}", pacienteId);
                    return new RuntimeException("Patient not found with ID: " + pacienteId);
                });

        List<Recomendacion> recommendations = recomendacionRepository.findByPaciente_id(pacienteId);

        if (recommendations.isEmpty()){
            log.warn("No treatments found for patient ID: {}", pacienteId);
        } else {
            log.info("Found {} treatments for patient ID {}", recommendations.size(), pacienteId);
        }

        List<RecomendacionResponseDTO> responseList = recomendacionMapper.toResponseDTOList(recommendations);

        String message = recommendations.isEmpty()
                ? "No hay recomendaciones registradas para este paciente."
                : "Recomendaciones obtenidas exitosamente";

        log.info("Returning recommedations list response for patient ID {} with {} recommendations.",
                pacienteId, recommendations.size());
        return new RecomendacionListResponseDTO(responseList, message);
    }
}
