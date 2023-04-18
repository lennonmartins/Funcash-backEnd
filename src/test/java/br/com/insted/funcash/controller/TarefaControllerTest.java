package br.com.insted.funcash.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.insted.funcash.builders.TarefaBuilder;
import br.com.insted.funcash.builders.TarefaRequestDTOBuilder;
import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.dto.TarefaResponseDTO;
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
	void deve_incluir_uma_tarefa() throws Exception {
		int quantidadeEsperada = 1;
		TarefaRequestDTO tarefaRequestDTO = new TarefaRequestDTOBuilder().construir();

		this.mockMvc
				.perform(post("/api/v1/tarefas")
						.content(JsonUtil.toJson(tarefaRequestDTO))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		List<Tarefa> tarefaRetornados = tarefaRepository.findByNomeContainingIgnoreCase(tarefaRequestDTO.getNome());
		assertThat(tarefaRetornados.size()).isEqualTo(quantidadeEsperada);
		assertThat(tarefaRetornados.stream().map(Tarefa::getNome).toList())
				.contains(tarefaRequestDTO.getNome());
	}

	@Test
	void deve_retornar_uma_lista_de_tarefas() throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, Exception{
		cadastrardezTarefas();
		int quantidadeEsperada = 10;

		TarefaResponseDTO[] tarefasRetornadas = JsonUtil.mapFromJsonModuleJavaTime(this.mockMvc.perform(get("/api/v1/tarefas")).andReturn().getResponse().getContentAsString(), TarefaResponseDTO[].class);

		assertThat(tarefasRetornadas).hasSize(quantidadeEsperada);
	}

	private void cadastrardezTarefas() {
		for (int i = 0; i < 10; i++) {
			tarefaRepository.save(new TarefaBuilder().construir());
		}
	}

	@Test
	void deve_retornar_uma_tarefa_pelo_id() throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, Exception{
		Tarefa tarefa = new TarefaBuilder().construir();
		tarefaRepository.save(tarefa);

		TarefaResponseDTO tarefaResponseDTO = JsonUtil.mapFromJsonModuleJavaTime((this.mockMvc.perform(get("/api/v1/tarefas/" + tarefa.getId())).andReturn()).getResponse().
		getContentAsString(), TarefaResponseDTO.class);

		assertThat(tarefaResponseDTO.getId()).isEqualTo(tarefa.getId());
	}
}
