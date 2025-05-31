package br.com.fiap.tech_challenge_1.mapper;

import br.com.fiap.tech_challenge_1.dto.request.EnderecoDTO;
import br.com.fiap.tech_challenge_1.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoDTO toEnderecoDTO(Endereco endereco);
    Endereco toEndereco(EnderecoDTO enderecoDTO);
}
