package com.iconiclinc.clinica_api.controller;

import com.iconiclinc.clinica_api.dto.request.TratamientoRequestDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoResponseDTO;
import com.iconiclinc.clinica_api.entity.Tratamiento;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
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
    private static final Logger log = LoggerFactory.getLogger(TratamientoController.class);

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
    public ResponseEntity<TratamientoListResponseDTO> getTreatmentsByPatient(
            @PathVariable Integer pacienteId){
        return ResponseEntity.ok(tratamientoService.getTreatmentsByPatient(pacienteId));
    }
}
