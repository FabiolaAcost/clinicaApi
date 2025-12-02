package com.iconiclinc.clinica_api.controller;

import com.iconiclinc.clinica_api.dto.request.RutinaRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaResponseDTO;
import com.iconiclinc.clinica_api.entity.Rutina;
import com.iconiclinc.clinica_api.service.RutinaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    private final RutinaService rutinaService;

    public RutinaController(RutinaService rutinaService) {
        this.rutinaService = rutinaService;
    }

    @PostMapping("/{profesionalId}/pacientes/{pacienteId}")
    public ResponseEntity<RutinaResponseDTO> addRoutine(
            @PathVariable Integer profesionalId,
            @PathVariable Integer pacienteId,
            @Valid @RequestBody RutinaRequestDTO rutina) {
        return ResponseEntity.ok(rutinaService.addRoutine(pacienteId, profesionalId, rutina));
    }

    @GetMapping("/pacientes/{pacienteId}")
    public ResponseEntity<RutinaListResponseDTO> getRoutinesByPatient(@PathVariable Integer pacienteId){
        return ResponseEntity.ok(rutinaService.getRoutinesByPatient(pacienteId));
    }
}
