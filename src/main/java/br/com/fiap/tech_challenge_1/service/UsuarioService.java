package br.com.fiap.tech_challenge_1.service;

import br.com.fiap.tech_challenge_1.controller.dto.Usuario;
import br.com.fiap.tech_challenge_1.controller.dto.UsuarioLogin;
import br.com.fiap.tech_challenge_1.exception.AutenticacaoException;
import br.com.fiap.tech_challenge_1.repository.UsuarioRepository;
import br.com.fiap.tech_challenge_1.repository.entity.UsuarioEntity;
import br.com.fiap.tech_challenge_1.utis.PasswordHasher;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  @Autowired private UsuarioRepository usuarioRepository;

  @Autowired private PasswordHasher passwordHasher;

  public void save(Usuario usuario) {

    if (usuarioRepository.findByLogin(usuario.login()).isPresent()) {
      throw new RuntimeException("Login já está em uso");
    }

    usuarioRepository.save(
        new UsuarioEntity(
            usuario.nome(),
            usuario.email(),
            usuario.login(),
            passwordHasher.hashPassword(usuario.senha()),
            usuario.endereco()));
  }

  public Set<Usuario> findAll() {
    return usuarioRepository.findAll().stream()
        .map(
            usuario ->
                new Usuario(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getLogin(),
                    usuario.getSenha(),
                    usuario.getEndereco()))
        .collect(Collectors.toSet());
  }

  public boolean login(UsuarioLogin usuarioLogin) {
    Optional<UsuarioEntity> login = usuarioRepository
            .findByLogin(usuarioLogin.login());
    if (login.isEmpty()) {
      throw new AutenticacaoException("Login não encontrado");
    }
    return login
        .map(usuario -> passwordHasher.verifyPassword(usuarioLogin.senha(), usuario.getSenha()))
        .orElse(false);
  }

  public void delete(Long id) {
      usuarioRepository.deleteById(id);
  }
}
