package br.com.insted.funcash.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.insted.funcash.builders.TarefaBuilder;
import br.com.insted.funcash.models.Tarefa;

@DataJpaTest
public class TarefaRepositoryTest {
    
    @Autowired
    private TarefaRepository tarefaRepository;

   @BeforeEach
   void setUp(){
    tarefaRepository.deleteAll();
   }

   @Test
   void deve_registrar_uma_tarefa_no_banco_com_id(){
        Tarefa tarefa = new TarefaBuilder().construir();

        tarefaRepository.save(tarefa);

        assertThat(tarefa.getId()).isNotNull();
   }

   @Test
   void deve_salvar_uma_tarefa_com_data_limite(){
     LocalDate dataLimiteEsperada = LocalDate.of(2023, 04, 17);
     Tarefa tarefa = new TarefaBuilder().comDataLimite(dataLimiteEsperada).construir();

     tarefaRepository.save(tarefa);

     assertEquals(dataLimiteEsperada, tarefa.getDataLimite());
     assertThat(tarefa.getDataLimite()).isEqualTo(dataLimiteEsperada);
   }

   @Test
   void deve_salvar_uma_tarefa_com_data_e_hora_limte(){
     LocalDate dataLimiteEsperada = LocalDate.of(2023, 4, 17);
        LocalTime horaLimiteEsperada = LocalTime.of(19, 30, 0);
        Tarefa tarefa = new TarefaBuilder().comHoraLimite(dataLimiteEsperada,horaLimiteEsperada).construir();

        tarefaRepository.save(tarefa);

        assertThat(tarefa.getHoraLimite()).isNotNull();
        assertThat(tarefa.getId()).isNotNull();
   }
}
