package com.iconiclinc.clinica_api.service;



import com.iconiclinc.clinica_api.dto.request.RecomendacionRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RecomendacionListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.RecomendacionResponseDTO;
import com.iconiclinc.clinica_api.entity.Recomendacion;
import com.iconiclinc.clinica_api.entity.Rutina;

import java.util.List;

public interface RecomendacionService {
    RecomendacionResponseDTO addRecommendation(Integer patientId, Integer professionalId, RecomendacionRequestDTO recomendacion);

    RecomendacionListResponseDTO getRecommendationsByPatient(Integer patientId);
}
