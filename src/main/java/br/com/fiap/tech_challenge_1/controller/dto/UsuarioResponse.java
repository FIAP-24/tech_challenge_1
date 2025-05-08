package br.com.fiap.tech_challenge_1.controller.dto;

import br.com.fiap.tech_challenge_1.repository.entity.UsuarioEntity;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String login,
        String endereco
) {
    public static UsuarioResponse fromEntity(UsuarioEntity entity) {
        return new UsuarioResponse(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getLogin(),
                entity.getEndereco()
        );
    }
}
