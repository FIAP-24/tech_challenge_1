package br.com.fiap.tech_challenge_1.dto.request;

import br.com.fiap.tech_challenge_1.domain.enuns.Perfil;
import jakarta.validation.constraints.*;

public record UsuarioRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email deve ser válido")
        String email,

        @NotNull
        @NotEmpty(message = "Perfil é obrigatório")
        Perfil perfil,

        @NotBlank(message = "Login é obrigatório")
        String login,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String senha,

        String endereco
) {}
