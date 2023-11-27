// package br.com.insted.funcash.controllers;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.io.UnsupportedEncodingException;
// import java.util.List;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonMappingException;

// import br.com.insted.funcash.builders.CriancaBuilder;
// import br.com.insted.funcash.builders.TarefaBuilder;
// import br.com.insted.funcash.builders.TarefaRequestDTOBuilder;
// import br.com.insted.funcash.dtos.TarefaRequestDTO;
// import br.com.insted.funcash.dtos.TarefaResponseDTO;
// import br.com.insted.funcash.dtos.TarefaResponsePaginadasDTO;
// import br.com.insted.funcash.models.Crianca;
// import br.com.insted.funcash.models.Tarefa;
// import br.com.insted.funcash.repositories.CriancaRepository;
// import br.com.insted.funcash.repositories.TarefaRepository;
// import br.com.insted.funcash.utils.JsonUtil;

// @SpringBootTest
// @AutoConfigureMockMvc
// @AutoConfigureTestDatabase
// public class TarefaControllerTest {
// 	@Autowired
// 	private MockMvc mockMvc;

// 	@Autowired
// 	private TarefaRepository tarefaRepository;

// 	@Autowired
// 	private CriancaRepository criancaRepository;

// 	private static int statusOk = 200;

// 	@BeforeEach
// 	@AfterEach
// 	public void deletarDados() {
// 		tarefaRepository.deleteAll();
// 	}

// 	@Test
// 	 void deve_incluir_uma_tarefa() throws Exception {
// 	 	int quantidadeEsperada = 1;
// 	 	Crianca criancaEsperada = new CriancaBuilder().construir();
// 	 	criancaRepository.save(criancaEsperada);
// 	 	TarefaRequestDTO tarefaRequestDTO = new TarefaRequestDTOBuilder()
// 	 			.comCrianca(criancaEsperada.getId())
// 	 			.construir();

// 	 	this.mockMvc
// 	 			.perform(post("/api/v1/tarefas")
// 	 					.content(JsonUtil.toJson(tarefaRequestDTO))
// 	 					.contentType(MediaType.APPLICATION_JSON))
// 	 			.andExpect(status().isCreated());

// 	 	List<Tarefa> tarefaRetornados = tarefaRepository.findByTituloContainingIgnoreCase(tarefaRequestDTO.getNome());
// 	 	assertThat(tarefaRetornados.size()).isEqualTo(quantidadeEsperada);
// 	 	assertThat(tarefaRetornados.stream().map(Tarefa::getTitulo).toList())
// 	 			.contains(tarefaRequestDTO.getNome());
// 	 }

// 	 @Test
// 	 void deve_retornar_uma_lista_de_tarefas()
// 	 		throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, Exception {
// 	 	cadastrardezTarefas();
// 	 	int quantidadeEsperada = 10;

// 	 	TarefaResponseDTO[] tarefasRetornadas = JsonUtil.mapFromJsonModuleJavaTime(
// 	 			this.mockMvc.perform(get("/api/v1/tarefas")).andReturn().getResponse().getContentAsString(),
// 	 			TarefaResponseDTO[].class);

// 	 	assertThat(tarefasRetornadas).hasSize(quantidadeEsperada);
// 	 }

// 	 private void cadastrardezTarefas() throws Exception {
// 	 	Crianca criancaEsperada = new CriancaBuilder().construir();
// 	 	criancaRepository.save(criancaEsperada);
// 	 	for (int i = 0; i < 10; i++) {
// 	 		tarefaRepository.save(
// 	 				new TarefaBuilder()
// 	 						.comCrianca(criancaEsperada)
// 	 						.construir());
// 	 	}
// 	 }

// 	 @Test
// 	 void deve_retornar_uma_tarefa_pelo_id()
// 	 		throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, Exception {
// 	 	Tarefa tarefa = new TarefaBuilder().construir();
// 	 	tarefaRepository.save(tarefa);

// 	 	TarefaResponseDTO tarefaResponseDTO = JsonUtil
// 	 			.mapFromJsonModuleJavaTime((this.mockMvc.perform(get("/api/v1/tarefas/" + tarefa.getId())).andReturn())
// 	 					.getResponse().getContentAsString(), TarefaResponseDTO.class);

// 	 	assertThat(tarefaResponseDTO.getId()).isEqualTo(tarefa.getId());
// 	 }

// 	 @Test
// 	 void deve_retornar_uma_tarefa_atualizada() throws JsonProcessingException, Exception {
// 	 	Tarefa tarefa = new TarefaBuilder().construir();
// 	 	tarefaRepository.save(tarefa);
// 	 	String nomeEsperado = "Limpar caixa do gato";
// 	 	TarefaRequestDTO tarefaRequestDTO = new TarefaRequestDTOBuilder()
// 	 			.comTitulo(nomeEsperado)
// 	 			.construir();

// 	 	this.mockMvc
// 	 			.perform(put("/api/v1/tarefas/" + tarefa.getId())
// 	 					.content(JsonUtil.toJson(tarefaRequestDTO))
// 	 					.contentType(MediaType.APPLICATION_JSON))
// 	 			.andExpect(status().isOk());

// 	 	Iterable<Tarefa> tarefasEncontradas = tarefaRepository.findAll();
// 	 	assertThat(tarefasEncontradas).extracting(Tarefa::getTitulo).containsOnly(nomeEsperado);
// 	 }

// 	 @Test
// 	 void deve_retornar_tarefas_paginadas() throws Exception{
// 		cadastrardezTarefas();
// 		int quantidadePorPagina = 4;
// 		int totalPaginasEsperadas = 3;

// 		MvcResult mvcResult = mockMvc.perform(get("/api/v1/tarefas?pagina=0&tamanho="+ quantidadePorPagina +"&campoOrdenacao=dataLimite&direcao=ASC")).andReturn();

// 		int status  = mvcResult.getResponse().getStatus();
// 		assertThat(status).isEqualTo(statusOk);
// 		TarefaResponsePaginadasDTO paginaDto = JsonUtil.mapFromJsonModuleJavaTime(mvcResult.getResponse().getContentAsString(), TarefaResponsePaginadasDTO.class);
// 		assertThat(paginaDto.getTotalDePaginas()).isEqualTo(totalPaginasEsperadas);
// 	 }
// }
