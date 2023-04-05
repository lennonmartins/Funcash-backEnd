package br.com.insted.funcash.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.insted.funcash.builders.TarefaBuilder;
import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.models.Tarefa;
import br.com.insted.funcash.repository.TarefaRepository;
import br.com.insted.funcash.utils.JsonUtil;

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
	public void deve_incluir_uma_tarefa() throws Exception {
		int quantidadeEsperada = 1;
		String horaLimiteEmString = "19:30:00";
		String dataLimite = "2023-03-23";
		double valor = 30.0;
		String nome = "Tirar lixo";

		TarefaRequestDTO tarefaRequestDTO = new TarefaRequestDTO(horaLimiteEmString, dataLimite, valor, nome);

		this.mockMvc
				.perform(post("/api/v1/tarefas")
						.content(JsonUtil.toJson(tarefaRequestDTO))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		List<Tarefa> tarefaRetornados = tarefaRepository.findByNomeContainingIgnoreCase(tarefaRequestDTO.getNome());
		Assertions.assertThat(tarefaRetornados.size()).isEqualTo(quantidadeEsperada);
		Assertions.assertThat(tarefaRetornados.stream().map(Tarefa::getNome).toList())
				.contains(tarefaRequestDTO.getNome());

	}

	// private String toJson(Tarefa tarefa) throws JsonProcessingException {
	// ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	// String json = ow.writeValueAsString(tarefa);
	// return json;
	// };

}
