package br.com.insted.funcash.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.insted.funcash.builders.CriancaBuilder;
import br.com.insted.funcash.builders.CriancaRequestDTOBuilder;
import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.repository.CriancaRepository;
import br.com.insted.funcash.repository.ResponsavelRepository;
import br.com.insted.funcash.service.CriancaService;
import br.com.insted.funcash.utils.DataConvert;

@SpringBootTest
public class CriancaServiceTest {
    
    @Mock
    private CriancaService criancaService;
    
    @Mock
    ResponsavelRepository responsavelRepository;

    @Mock
    CriancaRepository criancaRepository;

    @BeforeEach
    @AfterEach
    void setUp(){
        responsavelRepository.deleteAll();
        criancaRepository.deleteAll();
    }
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

    @Test
    void deve_buscar_uma_lista_de_crianca_pelo_id_do_responsavel() throws Exception{
        Long idResponsavel = 1L;
        int quantidadeEsperada = 2;
        Crianca criancaVelha = new CriancaBuilder().construir();
        Crianca criancaNova = new CriancaBuilder().construir();
        Collection <Crianca> listaDeCriancas = Arrays.asList(criancaNova,criancaVelha);

        when(criancaRepository.findAllByResponsavel(idResponsavel)).thenReturn(listaDeCriancas);
        Collection<CriancaResponseDTO> criancasRetornadas = criancaService.buscarCriancasPeloResponsavel(idResponsavel);
        
        assertNotNull(criancasRetornadas);       
        assertEquals(quantidadeEsperada, criancasRetornadas.size());
      }
}
