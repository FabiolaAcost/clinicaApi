package com.iconiclinc.clinica_api.controller;

import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.service.ProfesionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesional")
public class ProfesionalController {
    private static final Logger log = LoggerFactory.getLogger(ProfesionalController.class);

    private final ProfesionalService profesionalService;

    public ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    @PostMapping("/{profesionalId}/pacientes")
    public ResponseEntity<Paciente> addPacient(@PathVariable Integer profesionalId, @RequestBody Paciente paciente){
        log.info("Recieved request to add patient for professional ID {}", profesionalId);
        Paciente saved = profesionalService.addPatient(paciente, profesionalId);
        log.info("Patiente created successfully with ID {}", saved.getId());
        return ResponseEntity.ok(saved);
    }
    @GetMapping("/{profesionalId}/pacientes")
    public ResponseEntity<List<Paciente>> getPatientsByProfessional(@PathVariable Integer profesionalId){
        log.info("Fetching patients for professional ID: {}", profesionalId);
        List<Paciente> patients = profesionalService.getPatientsByProfessional(profesionalId);
        return ResponseEntity.ok(patients);
    }
}
