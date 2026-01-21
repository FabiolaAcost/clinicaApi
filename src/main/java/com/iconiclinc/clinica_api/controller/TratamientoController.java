package com.iconiclinc.clinica_api.controller;

import com.iconiclinc.clinica_api.dto.request.TratamientoRequestDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoResponseDTO;
import com.iconiclinc.clinica_api.service.TratamientoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {
    private final TratamientoService tratamientoService;

    public TratamientoController(TratamientoService tratamientoService) {
        this.tratamientoService = tratamientoService;
    }

    @PostMapping("/{profesionalId}/pacientes/{pacienteId}")
    public ResponseEntity<TratamientoResponseDTO> addTreatment(
            @PathVariable Integer profesionalId,
            @PathVariable Integer pacienteId,
            @Valid @RequestBody TratamientoRequestDTO tratamiento){
        return ResponseEntity.ok(tratamientoService.addTreatment(pacienteId, profesionalId, tratamiento));
    }

    @GetMapping("/pacientes/{pacienteId}")
    public ResponseEntity<List<TratamientoResponseDTO>> getTreatmentsByPatient(
            @PathVariable Integer pacienteId){
        return ResponseEntity.ok(tratamientoService.getTreatmentsByPatient(pacienteId));
    }

    @PutMapping("/{tratamientoId}")
    public ResponseEntity<TratamientoResponseDTO> updateTreatment(
            @PathVariable Integer tratamientoId,
            @Valid @RequestBody TratamientoRequestDTO requestDTO
    ){
        return ResponseEntity.ok(tratamientoService.updateTreatment(tratamientoId, requestDTO));
    }

    @DeleteMapping("/{tratamientoId}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Integer tratamientoId) {
        tratamientoService.deleteTreatment(tratamientoId);
        return ResponseEntity.noContent().build();
    }
}
