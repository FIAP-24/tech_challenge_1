package br.com.fiap.tech_challenge_1.dto.response;

/**
 * DTO for user responses (excludes sensitive data like password)
 */
public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String login,
        String endereco
) {}