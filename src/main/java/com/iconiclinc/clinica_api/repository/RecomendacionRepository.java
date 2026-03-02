package com.iconiclinc.clinica_api.repository;

import com.iconiclinc.clinica_api.entity.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Integer> {
    List<Recomendacion> findByPacienteId(Integer pacienteId);

    List<Recomendacion> findByProfesional_id(Integer profesionalId);
}
