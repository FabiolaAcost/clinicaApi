package com.iconiclinc.clinica_api.service;

import com.iconiclinc.clinica_api.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public Optional<Usuario> findByEmail(String email);
    public Usuario login(String email, String password);
    public Boolean existsByEmail(String email);
    public Boolean existByRut(String rut);
    public Usuario save(Usuario usuario);
    public List<Usuario> findAll();

    public Usuario findById(Integer id);

    /*
    public Usuario update(Usuario usuario);

    public void delete(Integer id);
    */
}
