package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.RecomendacionRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RecomendacionResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.entity.Recomendacion;
import com.iconiclinc.clinica_api.exception.PatientNotFoundException;
import com.iconiclinc.clinica_api.exception.ProfessionalNotFoundException;
import com.iconiclinc.clinica_api.exception.RecommendationNotFoundException;
import com.iconiclinc.clinica_api.exception.BusinessException;
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
    public RecomendacionResponseDTO addRecommendation(Integer pacienteId, String email, RecomendacionRequestDTO recomendacion) {
        log.info("Attempting to add new recommendation for patient ID {} by professional email {}", pacienteId, email);

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID {}", pacienteId);
                    return new PatientNotFoundException(pacienteId);
                });

        Profesional profesional = profesionalRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> {
                    log.error("Professional not found with email {}", email);
                    return new ProfessionalNotFoundException(email);
                });

        if (!paciente.getProfesional().getId().equals(profesional.getId())) {
            log.error("Professional {} tried to modify patient {} not assigned to them",
                    email, pacienteId);
            throw new BusinessException("You are not allowed to modify this patient");
        }

        Recomendacion recomend = recomendacionMapper.toEntity(recomendacion, paciente, profesional);
        Recomendacion saved = recomendacionRepository.save(recomend);
        log.info("Recommendation saved successfully with ID {}",
                saved.getId());
        return recomendacionMapper.toResponseDTO(saved);
    }

    @Override
    public List<RecomendacionResponseDTO> getRecommendationsByPatient(Integer pacienteId) {
        log.info("Fetching all recommendations for patient ID: {}", pacienteId);

        pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID {}", pacienteId);
                    return new PatientNotFoundException(pacienteId);
                });

        List<Recomendacion> recommendations = recomendacionRepository.findByPacienteId(pacienteId);

        if (recommendations.isEmpty()){
            log.warn("No recommendations found for patient ID: {}", pacienteId);
        } else {
            log.info("Found {} recommendations for patient ID {}", recommendations.size(), pacienteId);
        }

        return recomendacionMapper.toResponseDTOList(recommendations);
    }

    @Override
    public RecomendacionResponseDTO updateRecommendation(Integer recommendationId, RecomendacionRequestDTO requestDTO) {
        log.info("Updating recommendation description for recommendation ID {}", recommendationId);
        Recomendacion recomendacion = recomendacionRepository.findById(recommendationId)
                .orElseThrow(() ->{
                    log.error("Recommendation not found with id {}", recommendationId);
                    return new RecommendationNotFoundException(recommendationId);
                });

        recomendacion.setDescripcion(requestDTO.getDescripcion());
        Recomendacion updated = recomendacionRepository.save(recomendacion);
        return recomendacionMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteRecommendation(Integer recommendationId) {
        log.info("Deleting recommendation with ID {}", recommendationId);
        Recomendacion recomendacion = recomendacionRepository.findById(recommendationId)
                .orElseThrow(() ->{
                    log.error("Recommendation not found with id {}", recommendationId);
                    return new RecommendationNotFoundException(recommendationId);
                });

        recomendacionRepository.delete(recomendacion);
        log.info("Recommendation ID {} deleted successfully", recommendationId);

    }
}
