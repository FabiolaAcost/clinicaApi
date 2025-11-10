package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Usuario;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService{
    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private  final PacienteService pacienteService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              BCryptPasswordEncoder passwordEncoder,
                              PacienteRepository pacienteRepository,
                              PacienteService pacienteService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.pacienteRepository = pacienteRepository;
        this.pacienteService = pacienteService;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        log.debug("Looking up user by email {} ", email);
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario login(String email, String password) {
        log.info("Attempting login for email {}", email);

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User with email {} not found", email);
                    return new RuntimeException("Invalid email or password");
                });


        if (!passwordEncoder.matches(password, usuario.getContrasena())){
            log.error("Invalid password for email {} ", email);
            throw new RuntimeException("Invalid email or password");
        }

        log.info("Login successful for user {}", usuario.getEmail());
        return usuario;
    }

    @Override
    public Boolean existsByEmail(String email) {
        boolean exists = usuarioRepository.existsByEmail(email);
        log.debug("Email {} exists? {}", email, exists);
        return exists;
    }

    @Override
    public Boolean existByRut(String rut) {
        boolean exists = usuarioRepository.existsByRut(rut);
        log.debug("RUT {}, exists? {}", rut, exists);
        return exists;
    }

    @Override
    public Usuario save(Usuario usuario) {
        log.info("Attempting tu register user with RUT: {}", usuario.getRut());

        if (existsByEmail(usuario.getEmail())) {
            log.warn("Email {} already registered", usuario.getEmail());
            throw new RuntimeException("Email already registered");
        }
        if (existByRut(usuario.getRut())) {
            log.warn("RUT {} already registered", usuario.getRut());
            throw new RuntimeException("RUT already registered");
        }

        Optional<Paciente> pacienteOpt = pacienteService.findByRut(usuario.getRut());
        if (pacienteOpt.isEmpty()){
            log.warn("RUT {} not found in 'paciente' table. Registration denied.", usuario.getRut());
            throw new RuntimeException("Patient not found. Must be added by a professional first.");
        }

        Paciente paciente = pacienteOpt.get();

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        Usuario savedUser = usuarioRepository.save(usuario);
        log.info("User saved successfully with ID {}", savedUser.getId());

        paciente.setUsuario(savedUser);
        pacienteRepository.save(paciente);
        log.info("Linked user ID {} with patient ID {}", savedUser.getId(), paciente.getId());
        return savedUser;
    }

    @Override
    public List<Usuario> findAll() {
        log.info("Fetching all users");
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        log.info("Searching user with id: {}", id);
        return usuarioRepository.findById(id)
                .orElseThrow(() ->{
                    log.error("user with id {} not found", id);
                    return new RuntimeException("User not found with id: " + id);
                });
    }

    /*
    @Override
    public Usuario update(Usuario usuario) {
        log.info("Updating user with id: {}", usuario.getId());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delenting user with id: {}", id);
        usuarioRepository.deleteById(id);
    }
    */
}
