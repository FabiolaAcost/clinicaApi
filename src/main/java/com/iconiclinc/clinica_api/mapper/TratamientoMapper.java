package com.iconiclinc.clinica_api.mapper;

import com.iconiclinc.clinica_api.dto.request.TratamientoRequestDTO;
import com.iconiclinc.clinica_api.dto.response.TratamientoResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Profesional;
import com.iconiclinc.clinica_api.entity.TipoTratamiento;
import com.iconiclinc.clinica_api.entity.Tratamiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TratamientoMapper {
    public Tratamiento toEntity(TratamientoRequestDTO tratamientoRequestDTO, Paciente paciente, Profesional profesional){
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setTipo(TipoTratamiento.valueOf(tratamientoRequestDTO.getTipo()));
        tratamiento.setFecha(tratamientoRequestDTO.getFecha());
        tratamiento.setPaciente(paciente);
        tratamiento.setProfesional(profesional);
        return tratamiento;
    }

    public TratamientoResponseDTO toResponseDTO(Tratamiento tratamiento){
        return new TratamientoResponseDTO(
              tratamiento.getId(),
              tratamiento.getTipo().name(),
              tratamiento.getFecha(),
                tratamiento.getPaciente().getNombre(),
              tratamiento.getProfesional().getNombre()
        );
    }

    public List<TratamientoResponseDTO> toResponseDTOlist(List<Tratamiento> tratamientos){
        return tratamientos.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
