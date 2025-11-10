package com.iconiclinc.clinica_api.controller;

import com.iconiclinc.clinica_api.entity.Usuario;
import com.iconiclinc.clinica_api.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(ProfesionalController.class);
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario usuario){
        log.info("Received request to register user with RUT {}", usuario.getRut());
        Usuario nuevoUsuario = usuarioService.save(usuario);
        log.info("User registered successfully with ID {}", nuevoUsuario.getId());
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario){
        log.info("Received login request for email {}", usuario.getEmail());
       Usuario u = usuarioService.login(usuario.getEmail(), usuario.getContrasena());
        log.info("Login successful for user with email {}", u.getEmail());
       return ResponseEntity.ok(u);
    }
}
