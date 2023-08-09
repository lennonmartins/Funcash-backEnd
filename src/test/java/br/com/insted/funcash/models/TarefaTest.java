package br.com.insted.funcash.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.insted.funcash.builders.TarefaBuilder;

@SpringBootTest
public class TarefaTest {

    @Test
    void deve_criar_uma_tarefa() throws Exception{
        String nomeEsperado = "tirar lixo";

        Tarefa tarefa = new TarefaBuilder().comNome(nomeEsperado).construir();

       assertEquals(nomeEsperado, tarefa.getNome());
    }

    @Test
    void deve_criar_uma_tarefa_com_o_horario_atual() throws Exception{
       LocalDateTime horaEsperada = LocalDateTime.now();
        
        Tarefa tarefa = new TarefaBuilder().construir();

        assertEquals(horaEsperada.getHour(), tarefa.getDataDeCriacao().getHour());
    }

    @Test
    void deve_criar_uma_tarefa_com_data_limite() throws Exception{
        LocalDate dataLimiteEsperada = LocalDate.of(2023, 4,17);

        Tarefa tarefa = new TarefaBuilder().comDataLimite(dataLimiteEsperada).construir();

        assertEquals(dataLimiteEsperada, tarefa.getDataLimite());
    }

    @Test
    void deve_cadastar_uma_tarefa_com_data_e_hora_limte() throws Exception{
        LocalDate dataLimiteEsperada = LocalDate.of(2023, 4, 17);
        LocalTime horaLimiteEsperada = LocalTime.of(19, 30, 0);
        LocalDateTime horDateTimeCompleta = LocalDateTime.of(dataLimiteEsperada,horaLimiteEsperada);

        Tarefa tarefa = new TarefaBuilder().comHoraLimite(dataLimiteEsperada,horaLimiteEsperada).construir();

        assertEquals(horDateTimeCompleta, tarefa.getHoraLimite());
    }

    

    


}
