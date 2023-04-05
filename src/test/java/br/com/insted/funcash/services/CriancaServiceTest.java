package br.com.insted.funcash.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.insted.funcash.builders.CriancaRequestDTOBuilder;
import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.service.CriancaService;
import br.com.insted.funcash.utils.DataConvert;

@SpringBootTest
public class CriancaServiceTest {

    @Autowired
    private CriancaService criancaService;
    
    @Test
    void deve_converte_data_em_string_para_timestamp(){
        String dataEmString = "2010-07-19";
        
        LocalDate dataEsperada = LocalDate.of(2010, 07, 19);

        LocalDate dataAtual = DataConvert.obterData(dataEmString);

        assertEquals(dataEsperada, dataAtual);
    }

    @Test
    void deve_salvar_crianca_com_data_formatada(){
        String dataEmString = "2010-07-19";
        LocalDate dataEsperada = LocalDate.of(2010, 07, 19);
        CriancaRequestDTO criancaRequestDTO = new CriancaRequestDTOBuilder().comData(dataEmString).construir();

        CriancaResponseDTO criancaResponse = criancaService.cadastrar(criancaRequestDTO);

        assertThat(criancaResponse.getDataDeNascimento()).isEqualTo(dataEsperada);
    }

    @Test
    void deve_cadastrar_uma_crianca(){
        CriancaRequestDTO criancaRequestDTO = new CriancaRequestDTOBuilder().construir();

        CriancaResponseDTO criancaResponseDTO = criancaService.cadastrar(criancaRequestDTO);

        assertThat(criancaResponseDTO.getId()).isNotNull();
    }
}
