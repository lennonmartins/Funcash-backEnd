package br.com.insted.funcash.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.insted.funcash.builders.CriancaBuilder;
import br.com.insted.funcash.builders.TarefaRequestDTOBuilder;
import br.com.insted.funcash.dtos.TarefaRequestDTO;
import br.com.insted.funcash.dtos.TarefaResponseDTO;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.repositories.CriancaRepository;
import br.com.insted.funcash.repositories.TarefaRepository;

@SpringBootTest
public class TarefaServiceTest {
    
    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private CriancaRepository criancaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @BeforeEach
    @AfterEach
    void resetDb(){
        criancaRepository.deleteAll();
        tarefaRepository.deleteAll();
    }

    @Test
    void deve_cadastrar_uma_tarefa_com_hora_atual() throws Exception{
       TarefaRequestDTO tarefaRequestDTO = obterTarefaRequestComCrianca();
        
        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDTO);

        assertThat(tarefaResponseDTO.getId()).isNotNull();
        assertThat(tarefaResponseDTO.getDataDeCriacao()).isNotNull();
    }

    @Test
    void deve_cadastar_uma_tarefa_com_data_limite() throws Exception{
        String dataLimiteEmString = "2023-04-17";
        LocalDate dataLimiteEsperada = LocalDate.of(2023, 04, 17);
        Crianca criancaEsperada = new CriancaBuilder().construir();
		criancaRepository.save(criancaEsperada);
        TarefaRequestDTO tarefaRequestDTO = 
            new TarefaRequestDTOBuilder()
                .comCrianca(criancaEsperada.getId())
                .comDataLimite(dataLimiteEmString)
                .construir();

        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDTO);

        assertThat(tarefaResponseDTO.getDataLimite()).isEqualTo(dataLimiteEsperada);      
    }

    @Test
    void deve_cadastar_uma_tarefa_com_data_e_hora_limte() throws Exception{
        String horaLimiteEmString = "19:00";
        LocalTime horalimiteEsperada = LocalTime.of(19, 0);
        String dataLimiteEmString = "2023-04-17";
        LocalDate dataLimiteEsperada = LocalDate.of(2023, 04, 17);
        LocalDateTime horaLimteCompletaEsperada = LocalDateTime.of(dataLimiteEsperada, horalimiteEsperada);
        Crianca criancaEsperada = new CriancaBuilder().construir();
		criancaRepository.save(criancaEsperada);
        TarefaRequestDTO tarefaRequestDTO = 
                new TarefaRequestDTOBuilder()
                    .comCrianca(criancaEsperada.getId())
                    .comDataLimite(dataLimiteEmString)
                    .comHoraLimite(horaLimiteEmString)
                    .construir();

        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDTO);

        assertThat(horaLimteCompletaEsperada).isEqualTo(tarefaResponseDTO.getHoraLimite());
    }

    @Test
    void deve_buscar_uma_tarefa_pelo_id() throws Exception{
        long idEsperado = 2L;
        TarefaRequestDTO tarefaRequestDTO = obterTarefaRequestComCrianca();

        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDTO);

        assertThat(tarefaResponseDTO.getId()).isEqualTo(idEsperado);
    }

    private TarefaRequestDTO obterTarefaRequestComCrianca() throws Exception {
        Crianca criancaEsperada = new CriancaBuilder().construir();
		criancaRepository.save(criancaEsperada);
        TarefaRequestDTO tarefaRequestDTO = 
                new TarefaRequestDTOBuilder()
                .comCrianca(criancaEsperada.getId())
                .construir();
        return tarefaRequestDTO;
    }

    @Test
    void deve_atualizar_uma_tarefa() throws Exception{
        long idEsperado = 1L;
        String nomeTarefaEsperada = "lavar louça";
        TarefaRequestDTO tarefaRequestDto = obterTarefaRequestComCrianca();
        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDto);
        tarefaRequestDto.setNome(nomeTarefaEsperada);

        tarefaResponseDTO = tarefaService.alterar(tarefaRequestDto,idEsperado);

        assertThat(nomeTarefaEsperada).isEqualTo(tarefaResponseDTO.getNome());
    }

    @Test
    void deve_buscar_uma_lista_de_tarefas_de_uma_crianca(){
        

    }

}
