package com.iconiclinc.clinica_api.controller;


import com.iconiclinc.clinica_api.dto.request.RecomendacionRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RecomendacionListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.RecomendacionResponseDTO;
import com.iconiclinc.clinica_api.entity.Recomendacion;
import com.iconiclinc.clinica_api.service.RecomendacionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recomendaciones")
public class RecomendacionController {
    private static final Logger log = LoggerFactory.getLogger(RecomendacionController.class);
    private final RecomendacionService recomendacionService;

    public RecomendacionController(RecomendacionService recomendacionService) {
        this.recomendacionService = recomendacionService;
    }
    @PostMapping("/{profesionalId}/pacientes/{pacienteId}")
    public ResponseEntity<RecomendacionResponseDTO> addRecommendation(
            @PathVariable Integer profesionalId,
            @PathVariable Integer pacienteId,
            @Valid @RequestBody RecomendacionRequestDTO recomendacion){
        return ResponseEntity.ok(recomendacionService.addRecommendation(pacienteId, profesionalId, recomendacion));
    }

    @GetMapping("/pacientes/{pacienteId}")
    public ResponseEntity<RecomendacionListResponseDTO> getRecommendationsByPatient(
            @PathVariable Integer pacienteId){
        return ResponseEntity.ok(recomendacionService.getRecommendationsByPatient(pacienteId)) ;
    }
}
