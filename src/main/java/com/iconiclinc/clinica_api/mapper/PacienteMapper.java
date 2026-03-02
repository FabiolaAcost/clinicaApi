package com.iconiclinc.clinica_api.mapper;

import com.iconiclinc.clinica_api.dto.request.PacienteRequestDTO;
import com.iconiclinc.clinica_api.dto.response.PacienteResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    public Paciente toEntity(PacienteRequestDTO pacienteRequestDTO){
        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteRequestDTO.getNombre());
        paciente.setRut(pacienteRequestDTO.getRut());
        return paciente;
    }

    public PacienteResponseDTO toResponseDTO(Paciente paciente){
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getRut()
        );
    }
}
