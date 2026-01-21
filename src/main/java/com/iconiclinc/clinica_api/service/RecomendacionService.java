package com.iconiclinc.clinica_api.service;



import com.iconiclinc.clinica_api.dto.request.RecomendacionRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RecomendacionResponseDTO;

import java.util.List;

public interface RecomendacionService {
    RecomendacionResponseDTO addRecommendation(Integer patientId, Integer professionalId, RecomendacionRequestDTO requestDTO);
    List<RecomendacionResponseDTO> getRecommendationsByPatient(Integer patientId);
    RecomendacionResponseDTO updateRecommendation(Integer recommendationId, RecomendacionRequestDTO requestDTO);
    void deleteRecommendation(Integer recommendationId);
}
