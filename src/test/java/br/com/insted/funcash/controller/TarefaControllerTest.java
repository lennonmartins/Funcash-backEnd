package br.com.insted.funcash.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.assertj.core.api.Assertions;
import java.util.List;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import br.com.insted.funcash.builders.TarefaBuilder;
import br.com.insted.funcash.models.Tarefa;
import br.com.insted.funcash.repository.TarefaRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TarefaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TarefaRepository tarefaRepository;
    
    @BeforeEach
	@AfterEach
	public void deleteDados() {
		tarefaRepository.deleteAll();

	}

    @Test
	public void deve_incluir_uma_tarefa() throws Exception  {
		Tarefa tarefa = new TarefaBuilder().construir();
		String json = toJson(tarefa);

		this.mockMvc
				.perform(post("/tarefa")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());

		List<Tarefa> tarefaRetornados = tarefaRepository.findByNomeContainingIgnoreCase(tarefa.getNome());
		Assertions.assertThat(tarefaRetornados.size()).isEqualTo(1);
		Assertions.assertThat(tarefaRetornados.stream().map(Tarefa::getNome).toList()).contains(tarefa.getNome());

	}

    private String toJson(Tarefa tarefa) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(tarefa);
		return json;
	};
	
}//
