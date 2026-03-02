package com.iconiclinc.clinica_api.mapper;

import com.iconiclinc.clinica_api.dto.request.RecomendacionRequestDTO;
import com.iconiclinc.clinica_api.dto.response.RecomendacionResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.entity.Recomendacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecomendacionMapper {
    public Recomendacion toEntity(RecomendacionRequestDTO recomendacionRequestDTO, Paciente paciente, Profesional profesional){
        Recomendacion recomendacion = new Recomendacion();
        recomendacion.setDescripcion(recomendacionRequestDTO.getDescripcion());
        recomendacion.setPaciente(paciente);
        recomendacion.setProfesional(profesional);
        return recomendacion;
    }

    public RecomendacionResponseDTO toResponseDTO(Recomendacion recomendacion){
        return new RecomendacionResponseDTO(
                recomendacion.getId(),
                recomendacion.getDescripcion(),
                recomendacion.getPaciente().getNombre(),
                recomendacion.getProfesional().getNombre()
        );
    }

    public List<RecomendacionResponseDTO> toResponseDTOList(List<Recomendacion> recomendaciones){
        return recomendaciones.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
