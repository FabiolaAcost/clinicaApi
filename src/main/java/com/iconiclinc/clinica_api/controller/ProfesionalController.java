package com.iconiclinc.clinica_api.controller;

import com.iconiclinc.clinica_api.dto.request.PacienteRequestDTO;
import com.iconiclinc.clinica_api.dto.response.PacienteResponseDTO;
import com.iconiclinc.clinica_api.service.ProfesionalService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/pacientes")
    public ResponseEntity<PacienteResponseDTO> addPatient(
            Authentication authentication,
            @Valid @RequestBody PacienteRequestDTO requestDTO) {

        String email = authentication.getName();
        return ResponseEntity.ok(profesionalService.addPatientByEmail(email, requestDTO));
    }
    @GetMapping("/pacientes")
    public ResponseEntity<List<PacienteResponseDTO>> getPatientsByProfessional
            (Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(profesionalService.getPatientsByEmail(email)
        );
    }
}
