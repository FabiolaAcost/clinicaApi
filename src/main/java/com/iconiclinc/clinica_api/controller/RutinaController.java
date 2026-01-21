package com.iconiclinc.clinica_api.controller;

import com.iconiclinc.clinica_api.dto.request.RutinaRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaResponseDTO;
import com.iconiclinc.clinica_api.service.RutinaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<RutinaResponseDTO>> getRoutinesByPatient(
            @PathVariable Integer pacienteId){
        return ResponseEntity.ok(rutinaService.getRoutinesByPatient(pacienteId));
    }

    @PutMapping("/{rutinaId}")
    public ResponseEntity<RutinaResponseDTO> updateRoutine(
            @PathVariable Integer rutinaId,
            @Valid @RequestBody RutinaRequestDTO requestDTO
    ){
        return ResponseEntity.ok(rutinaService.updateRoutine(rutinaId, requestDTO));

    }
    @DeleteMapping("/{rutinaId}")
    public ResponseEntity<Void> deleteRoutine(
            @PathVariable Integer rutinaId
    ){
        rutinaService.deleteRoutine(rutinaId);
        return ResponseEntity.noContent().build();
    }
}
