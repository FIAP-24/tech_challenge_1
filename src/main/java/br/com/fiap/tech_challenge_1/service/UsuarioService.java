package br.com.fiap.tech_challenge_1.service;

import java.util.Set;

public interface UsuarioService {
    UsuarioResponse save(UsuarioRequest request);
    Set<UsuarioResponse> findAll();
    UsuarioResponse findById(Long id);
    boolean authenticate(UsuarioLoginRequest loginRequest);
    void delete(Long id);
}
