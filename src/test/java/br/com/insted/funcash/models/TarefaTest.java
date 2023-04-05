package br.com.insted.funcash.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isNotNull;

import java.text.ParseException;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.insted.funcash.builders.TarefaBuilder;

@SpringBootTest
public class TarefaTest {

    @Test
    void deve_criar_uma_tarefa() throws ParseException{
        String nomeEsperado = "tirar lixo";

        Tarefa tarefa = new TarefaBuilder().comNome(nomeEsperado).construir();

       assertEquals(nomeEsperado, tarefa.getNome());
    }

    @Test
    void deve_criar_uma_tarefa_com_o_horario_atual(){
       LocalDateTime horaEsperada = LocalDateTime.now();
        
        Tarefa tarefa = new TarefaBuilder().construir();

        assertEquals(horaEsperada, tarefa.getDataDeCriacao());
    }

    


}
