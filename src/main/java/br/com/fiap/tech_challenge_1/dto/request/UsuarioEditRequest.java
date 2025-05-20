package br.com.fiap.tech_challenge_1.dto.request;

import br.com.fiap.tech_challenge_1.domain.enums.Perfil;
import jakarta.validation.constraints.*;

public record UsuarioEditRequest(

        String nome,

        String email,

        Perfil perfil,

        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String senha,

        EnderecoDTO endereco
) {}
