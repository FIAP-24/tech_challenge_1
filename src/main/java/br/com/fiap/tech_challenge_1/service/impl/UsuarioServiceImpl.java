package br.com.fiap.tech_challenge_1.service.impl;

import br.com.fiap.tech_challenge_1.dto.request.UsuarioLoginRequest;
import br.com.fiap.tech_challenge_1.dto.request.UsuarioRequest;
import br.com.fiap.tech_challenge_1.dto.response.UsuarioResponse;
import br.com.fiap.tech_challenge_1.exception.AuthenticationException;
import br.com.fiap.tech_challenge_1.exception.DuplicateResourceException;
import br.com.fiap.tech_challenge_1.exception.ResourceNotFoundException;
import br.com.fiap.tech_challenge_1.mapper.UsuarioMapper;
import br.com.fiap.tech_challenge_1.model.Usuario;
import br.com.fiap.tech_challenge_1.repository.UsuarioRepository;
import br.com.fiap.tech_challenge_1.service.UsuarioService;
import br.com.fiap.tech_challenge_1.util.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of user service
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordHasher passwordHasher;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            PasswordHasher passwordHasher,
            UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordHasher = passwordHasher;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional
    public UsuarioResponse save(UsuarioRequest request) {
        // Check for duplicate login
        usuarioRepository.findByLogin(request.login()).ifPresent(u -> {
            throw new DuplicateResourceException("Login já está em uso");
        });

        // Create new user with hashed password
        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setSenha(passwordHasher.hashPassword(request.senha()));

        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean authenticate(UsuarioLoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByLogin(loginRequest.login())
                .orElseThrow(() -> new AuthenticationException("Login não encontrado"));

        boolean authenticated = passwordHasher.verifyPassword(loginRequest.senha(), usuario.getSenha());
        if (!authenticated) {
            throw new AuthenticationException("Senha incorreta");
        }

        return true;
    }

    @Override
    @Transactional
    public UsuarioResponse update(Long id, UsuarioRequest request) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));

        // Check if the new login is already used by another user
        usuarioRepository.findByLogin(request.login())
                .filter(u -> !u.getId().equals(id))
                .ifPresent(u -> {
                    throw new DuplicateResourceException("Login já está em uso por outro usuário");
                });

        // Update fields
        existingUsuario.setNome(request.nome());
        existingUsuario.setEmail(request.email());
        existingUsuario.setLogin(request.login());
        existingUsuario.setEndereco(request.endereco());

        // Only update password if provided
        if (request.senha() != null && !request.senha().isEmpty()) {
            existingUsuario.setSenha(passwordHasher.hashPassword(request.senha()));
        }

        Usuario updated = usuarioRepository.save(existingUsuario);
        return usuarioMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}