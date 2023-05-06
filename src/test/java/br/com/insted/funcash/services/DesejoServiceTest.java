package br.com.insted.funcash.services;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.insted.funcash.dto.DesejoRequestDTO;
import br.com.insted.funcash.dto.DesejoResponseDTO;
import br.com.insted.funcash.service.DesejoService;

@SpringBootTest
public class DesejoServiceTest {

    @Autowired
    private DesejoService desejoService;

    @Test
    void deve_cadastrar_um_desejo() {
        String nome = "livros HP";
        String descricao = "";
        double valor = 250.0;

        DesejoRequestDTO desejoRequestDTO = new DesejoRequestDTO(nome, descricao, valor);

        DesejoResponseDTO desejoResponseDTO = desejoService.cadastrar(desejoRequestDTO);

        assertThat(desejoResponseDTO.getId()).isNotNull();
    }

    @Test
    void deve_altera_um_desejo(){
        String nome = "livros HP";
        String descricao = "";
        double valor = 250.0;
        long idEsperado = 1L;
        String nomeEsperado = "Copo stanley";
        DesejoRequestDTO desejoRequestDTO = new DesejoRequestDTO(nome, descricao, valor);
        DesejoResponseDTO desejoResponseDTO = desejoService.cadastrar(desejoRequestDTO);

        desejoRequestDTO.setNome(nomeEsperado);
        desejoResponseDTO = desejoService.alterar(desejoRequestDTO,idEsperado );

        assertThat(idEsperado).isEqualTo(desejoResponseDTO.getId());
        assertThat(nomeEsperado).isEqualTo(desejoResponseDTO.getNome());
    }

}
