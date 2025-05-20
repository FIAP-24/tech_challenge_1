package br.com.fiap.tech_challenge_1.dto.response;

import br.com.fiap.tech_challenge_1.domain.enums.Perfil;
import br.com.fiap.tech_challenge_1.dto.request.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record UsuarioResponse(
        Long id,
        String nome,
        Perfil perfil,
        String email,
        String login,
        EnderecoDTO endereco,
        @JsonProperty("data_atualizacao")
        LocalDate dataUpdate
) {}