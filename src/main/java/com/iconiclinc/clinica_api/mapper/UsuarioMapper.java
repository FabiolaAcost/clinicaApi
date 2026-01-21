package com.iconiclinc.clinica_api.mapper;

import com.iconiclinc.clinica_api.dto.request.UsuarioRequestDTO;
import com.iconiclinc.clinica_api.dto.response.UsuarioListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.UsuarioResponseDTO;
import com.iconiclinc.clinica_api.entity.Rol;
import com.iconiclinc.clinica_api.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {
    public Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO, Rol rol){
        Usuario usuario = new Usuario();
        usuario.setRut(usuarioRequestDTO.getRut());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setContrasena(usuarioRequestDTO.getContrasena());
        usuario.setRol(rol);
        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getRut(),
                usuario.getRol().getNombre()
        );
    }

    public List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios){
        return usuarios.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}