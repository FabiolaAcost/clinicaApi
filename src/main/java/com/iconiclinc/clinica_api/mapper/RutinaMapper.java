package com.iconiclinc.clinica_api.mapper;

import com.iconiclinc.clinica_api.dto.request.RutinaRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RutinaResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.entity.Rutina;
import com.iconiclinc.clinica_api.entity.TipoRutina;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RutinaMapper {
    public Rutina toEntity(RutinaRequestDTO rutinaRequestDTO, Paciente paciente, Profesional profesional){
        Rutina rutina = new Rutina();
        rutina.setTipo(TipoRutina.valueOf(rutinaRequestDTO.getTipo()));
        rutina.setDescripcion(rutinaRequestDTO.getDescripcion());
        rutina.setProfesional(profesional);
        rutina.setPaciente(paciente);
        return rutina;
    }

    public RutinaResponseDTO toResponseDTO(Rutina rutina){
        return new RutinaResponseDTO(
                rutina.getId(),
                rutina.getTipo().name(),
                rutina.getDescripcion(),
                rutina.getProfesional().getNombre()
        );
    }

    public List<RutinaResponseDTO> toResponseDTOList(List<Rutina> rutinas){
        return rutinas.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
