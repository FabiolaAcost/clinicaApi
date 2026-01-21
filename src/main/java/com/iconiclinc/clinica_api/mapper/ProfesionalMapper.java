package com.iconiclinc.clinica_api.mapper;

import com.iconiclinc.clinica_api.dto.request.ProfesionalRequestDTO;
import com.iconiclinc.clinica_api.dto.response.ProfesionalResponseDTO;
import com.iconiclinc.clinica_api.entity.Profesional;
import org.springframework.stereotype.Component;

@Component
public class ProfesionalMapper {
    public Profesional toEntity(ProfesionalRequestDTO profesionalRequestDTO){
        Profesional profesional = new Profesional();
        profesional.setNombre(profesionalRequestDTO.getNombre());
        profesional.setProfesion(profesionalRequestDTO.getProfesion());
        return profesional;
    }

    public ProfesionalResponseDTO toResponseDTO(Profesional profesional){
        return new ProfesionalResponseDTO(
                profesional.getNombre(),
                profesional.getProfesion()
        );
    }
}
