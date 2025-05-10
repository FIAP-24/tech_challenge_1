package br.com.fiap.tech_challenge_1.dto.response;

import br.com.fiap.tech_challenge_1.domain.enuns.Perfil;

public record UsuarioResponse(
        Long id,
        String nome,
        Perfil perfil,
        String email,
        String login,
        String endereco
) {}