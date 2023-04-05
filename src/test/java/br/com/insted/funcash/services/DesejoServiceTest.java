package br.com.insted.funcash.services;

import org.assertj.core.api.Assertions;
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
    void deve_cadastrar_um_desejo(){
        String nome = "livros HP";
        String descricao = "";
        double valor = 250.0;

        DesejoRequestDTO desejoRequestDTO = new DesejoRequestDTO(nome, descricao, valor);

        DesejoResponseDTO desejoResponseDTO = desejoService.cadastrar(desejoRequestDTO);

        Assertions.assertThat(desejoResponseDTO.getId()).isNotNull();
    }
    

}
