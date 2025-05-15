package br.com.fiap.tech_challenge_1.mapper;

import br.com.fiap.tech_challenge_1.domain.enums.Perfil;
import br.com.fiap.tech_challenge_1.dto.request.UsuarioRequest;
import br.com.fiap.tech_challenge_1.dto.response.UsuarioResponse;
import br.com.fiap.tech_challenge_1.model.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {

    private final UsuarioMapper mapper = UsuarioMapper.INSTANCE;

    @Test
    void toResponse_deveMapearUsuarioParaUsuarioResponse() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João");
        usuario.setEmail("joao@email.com");
        usuario.setLogin("joao");
        usuario.setEndereco("Rua 1");
        usuario.setDataUpdate(LocalDate.now());

        UsuarioResponse response = mapper.toResponse(usuario);

        assertEquals(usuario.getId(), response.id());
        assertEquals(usuario.getNome(), response.nome());
        assertEquals(usuario.getEmail(), response.email());
        assertEquals(usuario.getLogin(), response.login());
        assertEquals(usuario.getEndereco(), response.endereco());
        assertEquals(usuario.getDataUpdate(), response.dataUpdate());
    }

    @Test
    void toResponseSet_deveMapearSetDeUsuariosParaSetDeUsuarioResponse() {
        Usuario usuario = new Usuario();
        usuario.setId(2L);
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setLogin("maria");
        usuario.setEndereco("Rua 2");
        usuario.setDataUpdate(LocalDate.now());

        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(usuario);

        Set<UsuarioResponse> responses = mapper.toResponseSet(usuarios);

        assertEquals(1, responses.size());
        UsuarioResponse response = responses.iterator().next();
        assertEquals(usuario.getNome(), response.nome());
    }

    @Test
    void toResponseList_deveMapearListDeUsuariosParaListDeUsuarioResponse() {
        Usuario usuario = new Usuario();
        usuario.setId(3L);
        usuario.setNome("Carlos");
        usuario.setEmail("carlos@email.com");
        usuario.setLogin("carlos");
        usuario.setEndereco("Rua 3");
        usuario.setDataUpdate(LocalDate.now());

        List<Usuario> usuarios = List.of(usuario);

        List<UsuarioResponse> responses = mapper.toResponseList(usuarios);

        assertEquals(1, responses.size());
        assertEquals(usuario.getNome(), responses.get(0).nome());
    }

    @Test
    void toEntity_deveMapearUsuarioRequestParaUsuario() {
        UsuarioRequest request = new UsuarioRequest("Ana", "ana@email.com", Perfil.CLIENTE,"ana", "Rua 4", "teste");

        Usuario usuario = mapper.toEntity(request);

        assertNull(usuario.getId());
        assertEquals(request.nome(), usuario.getNome());
        assertEquals(request.email(), usuario.getEmail());
        assertEquals(request.login(), usuario.getLogin());
        assertEquals(request.endereco(), usuario.getEndereco());
        assertNotNull(usuario.getDataUpdate());
        assertNull(usuario.getSenha());
    }

    @Test
    void toEntityComId_deveMapearUsuarioRequestParaUsuarioComId() {
        UsuarioRequest request = new UsuarioRequest("Ana", "ana@email.com", Perfil.CLIENTE,"ana", "Rua 4", "teste");
        Usuario usuario = mapper.toEntity(request, 10L);

        assertEquals(10L, usuario.getId());
        assertEquals(request.nome(), usuario.getNome());
        assertEquals(request.email(), usuario.getEmail());
        assertEquals(request.login(), usuario.getLogin());
        assertEquals(request.endereco(), usuario.getEndereco());
        assertNotNull(usuario.getDataUpdate());
        assertNull(usuario.getSenha());
    }

    @Test
    void toEntityComIdESenha_deveMapearUsuarioRequestParaUsuarioComIdESenha() {
        UsuarioRequest request = new UsuarioRequest("Ana", "ana@email.com", Perfil.CLIENTE,"ana", "Rua 4", "teste");
        String senhaHash = "senha123";
        Usuario usuario = mapper.toEntity(request, 20L, senhaHash);

        assertEquals(20L, usuario.getId());
        assertEquals(request.nome(), usuario.getNome());
        assertEquals(request.email(), usuario.getEmail());
        assertEquals(request.login(), usuario.getLogin());
        assertEquals(request.endereco(), usuario.getEndereco());
        assertEquals(senhaHash, usuario.getSenha());
        assertNotNull(usuario.getDataUpdate());
    }
}