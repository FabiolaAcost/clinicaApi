package com.iconiclinc.clinica_api.repository;

import com.iconiclinc.clinica_api.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
    List<Rutina> findByPaciente_id(Integer pacienteId);
}
