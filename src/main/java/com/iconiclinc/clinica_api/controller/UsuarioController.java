package com.iconiclinc.clinica_api.controller;

import com.iconiclinc.clinica_api.dto.request.LoginRequestDTO;
import com.iconiclinc.clinica_api.dto.request.UsuarioRequestDTO;
import com.iconiclinc.clinica_api.dto.response.LoginResponseDTO;
import com.iconiclinc.clinica_api.dto.response.UsuarioResponseDTO;
import com.iconiclinc.clinica_api.entity.Usuario;
import com.iconiclinc.clinica_api.mapper.UsuarioMapper;
import com.iconiclinc.clinica_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(
            @Valid @RequestBody UsuarioRequestDTO requestDTO){

        return ResponseEntity.ok(usuarioService.register(requestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO requestDTO){

       return ResponseEntity.ok(usuarioService.login(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUserById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(usuarioService.findById(id));
    }
}
