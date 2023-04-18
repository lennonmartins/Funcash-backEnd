package br.com.insted.funcash.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.insted.funcash.builders.TarefaRequestDTOBuilder;
import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.dto.TarefaResponseDTO;
import br.com.insted.funcash.service.TarefaService;

@SpringBootTest
public class TarefaServiceTest {
    
    @Autowired
    private TarefaService tarefaService;

    @Test
    void deve_cadastrar_uma_tarefa_com_hora_atual(){
       TarefaRequestDTO tarefaRequestDTO = new TarefaRequestDTOBuilder().construir();
        
        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDTO);

        assertThat(tarefaResponseDTO.getId()).isNotNull();
        assertThat(tarefaResponseDTO.getDataDeCriacao()).isNotNull();
    }

    @Test
    void deve_cadastar_uma_tarefa_com_data_limite(){
        String dataLimiteEmString = "2023-04-17";
        LocalDate dataLimiteEsperada = LocalDate.of(2023, 04, 17);
        TarefaRequestDTO tarefaRequestDTO = new TarefaRequestDTOBuilder().comDataLimite(dataLimiteEmString).construir();

        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDTO);

        assertThat(tarefaResponseDTO.getDataLimite()).isEqualTo(dataLimiteEsperada);      
    }

    @Test
    void deve_cadastar_uma_tarefa_com_data_e_hora_limte(){
        String horaLimiteEmString = "19:00";
        LocalTime horalimiteEsperada = LocalTime.of(19, 0);
        String dataLimiteEmString = "2023-04-17";
        LocalDate dataLimiteEsperada = LocalDate.of(2023, 04, 17);
        LocalDateTime horaLimteCompletaEsperada = LocalDateTime.of(dataLimiteEsperada, horalimiteEsperada);
        TarefaRequestDTO tarefaRequestDTO = new TarefaRequestDTOBuilder().comDataLimite(dataLimiteEmString).comHoraLimite(horaLimiteEmString).construir();

        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDTO);

        assertThat(horaLimteCompletaEsperada).isEqualTo(tarefaResponseDTO.getHoraLimite());
    }

    @Test
    void deve_buscar_uma_tarefa_pelo_id(){
        long idEsperado = 1L;
        TarefaRequestDTO tarefaRequestDTO = new TarefaRequestDTOBuilder().construir();

        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDTO);

        assertThat(idEsperado).isEqualTo(tarefaResponseDTO.getId());
    }

    @Test
    void deve_atualizar_uma_tarefa(){
        long idEsperado = 1L;
        String nomeTarefaEsperada = "lavar louça";
        TarefaRequestDTO tarefaRequestDto = new TarefaRequestDTOBuilder().construir();
        TarefaResponseDTO tarefaResponseDTO = tarefaService.cadastrar(tarefaRequestDto);
        tarefaRequestDto.setNome(nomeTarefaEsperada);

        tarefaResponseDTO = tarefaService.alterar(tarefaRequestDto,idEsperado);

        assertThat(nomeTarefaEsperada).isEqualTo(tarefaResponseDTO.getNome());
    }

}
