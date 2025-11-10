package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.ProfesionalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalServiceImpl implements ProfesionalService{

    private static final Logger log = LoggerFactory.getLogger(ProfesionalServiceImpl.class);
    private final PacienteRepository pacienteRepository;
    private final ProfesionalRepository profesionalRepository;

    public ProfesionalServiceImpl(PacienteRepository pacienteRepository, ProfesionalRepository profesionalRepository) {
        this.pacienteRepository = pacienteRepository;
        this.profesionalRepository = profesionalRepository;
    }

    @Override
    public Paciente addPatient(Paciente paciente, Integer id) {
        log.info("Attempting to add new patient with RUT {}", paciente.getUsuario()!= null ? paciente.getUsuario().getRut() : "N/A");

        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional not found with ID: " + id));

        if (pacienteRepository.existsByRut(paciente.getUsuario().getRut())){
            log.warn("Patient with RUT {} already exists", paciente.getUsuario().getRut());
            throw new RuntimeException("Patient with this rut already exists");
        }

        if (pacienteRepository.existsByUsuario_Email(paciente.getUsuario().getEmail())){
            log.warn("Patient with email {} already exists", paciente.getUsuario().getEmail());
            throw new RuntimeException("Patient with this email already exists");
        }

        paciente.setProfesional(profesional);
        paciente.setUsuario(null);
        Paciente saved = pacienteRepository.save(paciente);
        log.info("New patient saved successfully with ID: {}", saved.getId());
        return saved;
    }

    @Override
    public List<Paciente> getPatientsByProfessional(Integer id) {
        log.info("Fetching all patients for professional ID: {}", id);
        return pacienteRepository.findByProfesional_id(id);
    }
}
