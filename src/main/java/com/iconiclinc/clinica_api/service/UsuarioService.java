package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.LoginRequestDTO;
import com.iconiclinc.clinica_api.dto.request.UsuarioRequestDTO;
import com.iconiclinc.clinica_api.dto.response.UsuarioListResponseDTO;
import com.iconiclinc.clinica_api.dto.response.UsuarioResponseDTO;
import com.iconiclinc.clinica_api.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public UsuarioResponseDTO login(LoginRequestDTO requestDTO);
    public UsuarioResponseDTO register(UsuarioRequestDTO requestDTO);
    public List<UsuarioResponseDTO> findAll();
    public UsuarioResponseDTO findById(Integer id);
}
