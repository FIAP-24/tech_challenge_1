package br.com.fiap.tech_challenge_1.mapper;

import br.com.fiap.tech_challenge_1.dto.request.UsuarioRequest;
import br.com.fiap.tech_challenge_1.dto.response.UsuarioResponse;
import br.com.fiap.tech_challenge_1.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * MapStruct mapper for converting between Usuario entity and DTOs
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    /**
     * Convert entity to response DTO
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "endereco", source = "endereco")
    UsuarioResponse toResponse(Usuario usuario);

    /**
     * Convert response DTOs to entities
     */
    Set<UsuarioResponse> toResponseSet(Set<Usuario> usuarios);

    List<UsuarioResponse> toResponseList(List<Usuario> usuarios);

    /**
     * Convert request DTO to entity without setting ID (for creation)
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataUpdate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "senha", ignore = true) // Password should be hashed separately
    Usuario toEntity(UsuarioRequest request);

    /**
     * Convert request DTO to entity with ID (for updates)
     */
    @Mapping(target = "dataUpdate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "senha", ignore = true) // Password should be hashed separately
    Usuario toEntity(UsuarioRequest request, Long id);

    default Usuario toEntity(UsuarioRequest request, Long id, String hashedPassword) {
        Usuario usuario = toEntity(request);
        usuario.setId(id);
        usuario.setSenha(hashedPassword);
        return usuario;
    }
}