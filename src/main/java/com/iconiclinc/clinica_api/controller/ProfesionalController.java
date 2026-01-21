package com.iconiclinc.clinica_api.controller;

import com.iconiclinc.clinica_api.dto.request.PacienteRequestDTO;
import com.iconiclinc.clinica_api.dto.response.PacienteResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.mapper.PacienteMapper;
import com.iconiclinc.clinica_api.service.ProfesionalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesional")
public class ProfesionalController {
    private final ProfesionalService profesionalService;

    public ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    @PostMapping("/{profesionalId}/pacientes")
    public ResponseEntity<PacienteResponseDTO> addPatient(
            @PathVariable Integer profesionalId,
            @Valid @RequestBody PacienteRequestDTO requestDTO) {

        return ResponseEntity.ok(profesionalService.addPatient(profesionalId, requestDTO));
    }
    @GetMapping("/{profesionalId}/pacientes")
    public ResponseEntity<List<PacienteResponseDTO>> getPatientsByProfessional
            (@PathVariable Integer profesionalId){

        return ResponseEntity.ok(profesionalService.getPatientsByProfessional(profesionalId)
        );
    }
}
