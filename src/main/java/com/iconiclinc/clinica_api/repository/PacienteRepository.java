package com.iconiclinc.clinica_api.repository;

import com.iconiclinc.clinica_api.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Optional<Paciente> findByRut(String rut);
    boolean existsByRut(String rut);

    boolean existsByUsuario_Email(String email);
    List<Paciente> findByProfesionalId(Integer id);

}
