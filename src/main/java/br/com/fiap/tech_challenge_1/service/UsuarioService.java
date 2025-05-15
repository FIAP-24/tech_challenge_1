package br.com.fiap.tech_challenge_1.service;

import br.com.fiap.tech_challenge_1.dto.request.UsuarioEditRequest;
import br.com.fiap.tech_challenge_1.dto.request.UsuarioLoginRequest;
import br.com.fiap.tech_challenge_1.dto.request.UsuarioRequest;
import br.com.fiap.tech_challenge_1.dto.response.UsuarioResponse;

import java.util.Set;

public interface UsuarioService {

  UsuarioResponse save(UsuarioRequest request);

  Set<UsuarioResponse> findAll();

  UsuarioResponse findById(Long id);

  boolean authenticate(UsuarioLoginRequest loginRequest);

  void delete(Long id);

  UsuarioResponse update(Long id, UsuarioEditRequest request);
}
