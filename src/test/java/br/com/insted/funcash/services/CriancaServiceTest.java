package br.com.insted.funcash.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.insted.funcash.builders.CriancaBuilder;
import br.com.insted.funcash.builders.CriancaRequestDTOBuilder;
import br.com.insted.funcash.builders.ResponsavelBuilder;
import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.mappers.CriancaMapper;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.repository.CriancaRepository;
import br.com.insted.funcash.repository.ResponsavelRepository;
import br.com.insted.funcash.service.CriancaService;
import br.com.insted.funcash.utils.DataConvert;

@SpringBootTest
public class CriancaServiceTest {
    
    @Mock
    ResponsavelRepository responsavelRepository;
    
    @Mock
    CriancaRepository criancaRepository; 
    
    @InjectMocks
    private CriancaService criancaService;
    
    @Mock
    CriancaMapper criancaMapper;

    @BeforeEach
    @AfterEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
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
    void deve_salvar_crianca_com_data_formatada() throws Exception{
        String dataEmString = "2010-07-19";
        LocalDate dataEsperada = LocalDate.of(2010, 07, 19);
        Responsavel responsavel = new ResponsavelBuilder().construir();
        Crianca crianca = new CriancaBuilder().construir();
        CriancaRequestDTO criancaRequestDTO = new CriancaRequestDTOBuilder()
            .comResponsavel(1L)
            .comData(dataEmString)
            .construir();
        CriancaResponseDTO criancaResponseDTO = CriancaResponseDTO.builder().dataDeNascimento(dataEsperada).build();

        when(responsavelRepository
        .findById(criancaRequestDTO.getIdDoResponsavel())).thenReturn(Optional.of(responsavel));
        when(criancaMapper.criancaRequestparaCrianca(criancaRequestDTO)).thenReturn(crianca);
        when(criancaRepository.save(any(Crianca.class))).thenReturn(crianca);
        when(criancaMapper.criancaParaCriancaResponseDTO(crianca)).thenReturn(criancaResponseDTO);
        
        CriancaResponseDTO criancaResponse = criancaService.cadastrar(criancaRequestDTO);
        assertThat(criancaResponse.getDataDeNascimento()).isEqualTo(dataEsperada);
    }

    @Test
    void deve_cadastrar_uma_crianca() throws Exception{
        CriancaRequestDTO criancaRequestDTO = extracted();

        CriancaResponseDTO criancaResponseDTO = criancaService.cadastrar(criancaRequestDTO);

        assertThat(criancaResponseDTO.getId()).isNotNull();
    }

    @Test
    void deve_editar_uma_crianca() throws Exception{
        Long idEsperado = 1L;
        String nomeParaSerAlterado = "Luluzinha Penosa";
        CriancaRequestDTO criancaRequestDTO = extracted();
        CriancaResponseDTO criancaResponseDTO = criancaService.cadastrar(criancaRequestDTO);

        criancaRequestDTO.setNome(nomeParaSerAlterado);

        criancaResponseDTO = criancaService.alterar(criancaRequestDTO, idEsperado);
        
        assertThat(criancaResponseDTO.getNome()).isEqualTo(nomeParaSerAlterado);
    }

    private CriancaRequestDTO extracted() throws Exception {
        Responsavel responsavel = new ResponsavelBuilder().construir();
		responsavelRepository.save(responsavel);
        CriancaRequestDTO criancaRequestDTO = new CriancaRequestDTOBuilder().comResponsavel(responsavel.getId()).construir();
        return criancaRequestDTO;
    }

    @Test
    void deve_buscar_uma_lista_de_crianca_pelo_id_do_responsavel() throws Exception{
        Long idResponsavel = 1L;
        int quantidadeEsperada = 2;
        Crianca filhoMaisVelho = new CriancaBuilder().construir();
        Crianca filhoMaisNovo = new CriancaBuilder().construir();
        Collection <Crianca> listaDeCriancas = Arrays.asList(filhoMaisNovo,filhoMaisVelho);

        CriancaResponseDTO filhoMaisNovoResponse = 
                new CriancaResponseDTO(
                    2L, 
                    filhoMaisNovo.getDataDeNascimento(),
                    filhoMaisNovo.getEmail(), 
                    filhoMaisNovo.getSenha(), 
                    filhoMaisNovo.getSaldo(),
                    filhoMaisNovo.getNome(),
                    filhoMaisNovo.getApelido(),
                    filhoMaisNovo.getGenero(),
                    filhoMaisNovo.getFoto());

        CriancaResponseDTO filhoMaisVelhoResponse = 
                new CriancaResponseDTO(
                    3L,
                    filhoMaisVelho.getDataDeNascimento(),
                    filhoMaisVelho.getEmail(), 
                    filhoMaisVelho.getSenha(), 
                    filhoMaisVelho.getSaldo(),
                    filhoMaisVelho.getNome(),
                    filhoMaisVelho.getApelido(),
                    filhoMaisVelho.getGenero(),
                    filhoMaisNovo.getFoto());

        Collection<CriancaResponseDTO> criancasRetornadasDTO = Arrays.asList(filhoMaisNovoResponse,filhoMaisVelhoResponse);
       
        when(criancaRepository.findAllByResponsavel(idResponsavel)).thenReturn(listaDeCriancas);
        when(criancaMapper.criancasParaCriancasResponsesDtos(listaDeCriancas)).thenReturn(criancasRetornadasDTO);
        
        Collection<CriancaResponseDTO> criancasRetornadas = criancaService.buscarCriancasPeloResponsavel(idResponsavel);
        
        assertNotNull(criancasRetornadas);       
        assertEquals(quantidadeEsperada, criancasRetornadas.size());
      }
}
