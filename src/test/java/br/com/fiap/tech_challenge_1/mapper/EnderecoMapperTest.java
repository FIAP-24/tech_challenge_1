package br.com.fiap.tech_challenge_1.mapper;

import br.com.fiap.tech_challenge_1.dto.request.EnderecoDTO;
import br.com.fiap.tech_challenge_1.model.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnderecoMapperTest {
    private final EnderecoMapper mapper = EnderecoMapper.INSTANCE;

    @Test
    public void testToEnderecoDTO() {
        Endereco endereco = new Endereco();
        endereco.setBairro("Paulista");
        endereco.setCep("01234567");
        endereco.setLogradouro("Av. Paulista");
        endereco.setNumero("123");
        endereco.setComplemento("AP");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");

        EnderecoDTO dto = mapper.toEnderecoDTO(endereco);

        assertNotNull(dto);
        assertEquals("Av. Paulista", dto.logradouro());
        assertEquals("123", dto.numero());
        assertEquals("São Paulo", dto.cidade());
        assertEquals("Paulista", dto.bairro());
        assertEquals("SP", dto.estado());
        assertEquals("AP", dto.complemento());
        assertEquals("01234567", dto.cep());
    }

    @Test
    public void testToEndereco() {
        EnderecoDTO enderecoDTO = new EnderecoDTO("Av. Paulista", "123", "AP", "Paulista", "São Paulo", "SP","01234567");

        Endereco endereco = mapper.toEndereco(enderecoDTO);

        assertNotNull(endereco);
        assertEquals("Av. Paulista", endereco.getLogradouro());
        assertEquals("123", endereco.getNumero());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("Paulista", endereco.getBairro());
        assertEquals("SP", endereco.getEstado());
        assertEquals("AP", endereco.getComplemento());
        assertEquals("01234567", endereco.getCep());
    }

    @Test
    public void testNullValues() {
        assertNull(mapper.toEnderecoDTO(null));
        assertNull(mapper.toEndereco(null));
    }
}
