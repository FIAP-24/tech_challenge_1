package br.com.fiap.tech_challenge_1.service;

import br.com.fiap.tech_challenge_1.controller.dto.UsuarioDTO;
import br.com.fiap.tech_challenge_1.repository.UsuarioRepository;
import br.com.fiap.tech_challenge_1.repository.entity.UsuarioEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  @Autowired private UsuarioRepository usuarioRepository;

  public void save(UsuarioDTO usuarioDTO) {
    usuarioRepository.save(
        new UsuarioEntity(
            usuarioDTO.nome(),
            usuarioDTO.email(),
            usuarioDTO.login(),
            usuarioDTO.senha(),
            usuarioDTO.endereco()));
  }

  public Set<UsuarioDTO> findAll() {

    List<UsuarioEntity> all = usuarioRepository.findAll();
    return all.stream()
            .map(
                    usuario ->
                            new UsuarioDTO(
                                    usuario.getNome(),
                                    usuario.getEmail(),
                                    usuario.getLogin(),
                                    usuario.getSenha(),
                                    usuario.getEndereco()))
            .collect(Collectors.toSet());

  }
}
