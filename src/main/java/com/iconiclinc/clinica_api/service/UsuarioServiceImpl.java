package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.dto.request.LoginRequestDTO;
import com.iconiclinc.clinica_api.dto.request.UsuarioRequestDTO;
import com.iconiclinc.clinica_api.dto.response.LoginResponseDTO;
import com.iconiclinc.clinica_api.dto.response.UsuarioResponseDTO;
import com.iconiclinc.clinica_api.entity.Paciente;
import com.iconiclinc.clinica_api.entity.Rol;
import com.iconiclinc.clinica_api.entity.Usuario;
import com.iconiclinc.clinica_api.exception.BusinessException;
import com.iconiclinc.clinica_api.exception.UserNotFoundException;
import com.iconiclinc.clinica_api.mapper.UsuarioMapper;
import com.iconiclinc.clinica_api.repository.PacienteRepository;
import com.iconiclinc.clinica_api.repository.RolRepository;
import com.iconiclinc.clinica_api.repository.UsuarioRepository;
import com.iconiclinc.clinica_api.security.JwtService;
import org.hibernate.usertype.BaseUserTypeSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService{
    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;
    private  final PacienteService pacienteService;
    private final RolRepository rolRepository;
    private final JwtService jwtService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              BCryptPasswordEncoder passwordEncoder,
                              PacienteRepository pacienteRepository, UsuarioMapper usuarioMapper,
                              PacienteService pacienteService, RolRepository rolRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.pacienteRepository = pacienteRepository;
        this.usuarioMapper = usuarioMapper;
        this.pacienteService = pacienteService;
        this.rolRepository = rolRepository;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        log.info("Attempting login for email {}", requestDTO.getEmail());

        Usuario usuario = usuarioRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> {
                    log.warn("User with email {} not found", requestDTO.getEmail());
                    return new BusinessException("Invalid email or password");
                });


        if (!passwordEncoder.matches(requestDTO.getContrasena(), usuario.getContrasena())){
            log.warn("Invalid password for email {} ", requestDTO.getEmail());
            throw new BusinessException("Invalid email or password");
        }

        Map<String, Object> claims = Map.of(
                "userId", usuario.getId(),
                "role", usuario.getRol().getNombre()
        );

        String token = jwtService.generateToken(usuario.getEmail(), claims);

        log.info("Login successful for user {}", usuario.getEmail());

        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toResponseDTO(usuario);

        return new LoginResponseDTO(token, "Bearer", usuarioResponseDTO);
    }

    private Boolean existsByEmail(String email) {
        boolean exists = usuarioRepository.existsByEmail(email);
        log.debug("Email {} exists? {}", email, exists);
        return exists;
    }

    private Boolean existsByRut(String rut) {
        boolean exists = usuarioRepository.existsByRut(rut);
        log.debug("RUT {}, exists? {}", rut, exists);
        return exists;
    }

    @Override
    public UsuarioResponseDTO register(UsuarioRequestDTO requestDTO) {
        log.info("Attempting to register user with RUT: {}", requestDTO.getRut());

        if (existsByEmail(requestDTO.getEmail())) {
            log.warn("Email {} already registered", requestDTO.getEmail());
            throw new BusinessException("Email already registered");
        }

        if (existsByRut(requestDTO.getRut())) {
            log.warn("RUT {} already registered", requestDTO.getRut());
            throw new BusinessException("RUT already registered");
        }

        Paciente paciente = pacienteService.getPacienteByRut(requestDTO.getRut());

        Rol rol = rolRepository.findByNombre("PACIENTE")
                        .orElseThrow(() ->{
                            log.error("Role PACIENTE not found in database");
                            return new BusinessException("Role not found");
                        });

        Usuario usuario = usuarioMapper.toEntity(requestDTO, rol);
        usuario.setContrasena(passwordEncoder.encode(requestDTO.getContrasena()));

        Usuario savedUser = usuarioRepository.save(usuario);
        log.info("User saved successfully with ID {}", savedUser.getId());

        paciente.setUsuario(savedUser);
        pacienteRepository.save(paciente);
        log.info("Linked user ID {} with patient ID {}", savedUser.getId(), paciente.getId());
        return usuarioMapper.toResponseDTO(savedUser);
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        log.info("Fetching all users");
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toResponseDTOList(usuarios);
    }

    @Override
    public UsuarioResponseDTO findById(Integer id) {
        log.info("Searching user with id: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("user with id {} not found", id);
                    return new UserNotFoundException(id);
                });
        return usuarioMapper.toResponseDTO(usuario);
    }

}
