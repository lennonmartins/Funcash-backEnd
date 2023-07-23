package br.com.insted.funcash.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.insted.funcash.builders.DesejoBuilder;
import br.com.insted.funcash.dto.DesejoRequestDTO;
import br.com.insted.funcash.dto.DesejoResponseDTO;
import br.com.insted.funcash.models.Desejo;
import br.com.insted.funcash.repository.DesejoRepository;
import br.com.insted.funcash.utils.JsonUtil;


@SpringBootTest
@AutoConfigureMockMvc
public class DesejoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DesejoRepository desejoRepository;

    @BeforeEach
    @AfterEach
    public void deleteDados(){
        desejoRepository.deleteAll();
    };

    @Test
	public void deve_incluir_um_desejo() throws Exception {
		int quantidadeEsperada = 1;
		String nome = "Video Game";
		String descricao = "PlayStation 1";
		double valor = 30;
		DesejoRequestDTO desejoRequestDTO = new DesejoRequestDTO(nome, descricao, valor);

		mockMvc.perform(post("/api/v1/desejos")
		.contentType(MediaType.APPLICATION_JSON)
		.content(JsonUtil.toJson(desejoRequestDTO)))
		.andExpect(status().isCreated());

		List<Desejo> desejoRetornados = desejoRepository.findByNomeContainingIgnoreCase(desejoRequestDTO.getNome());

		assertThat(desejoRetornados.size()).isEqualTo(quantidadeEsperada);
		assertThat(desejoRequestDTO.getNome()).isIn(desejoRetornados.stream().map(Desejo::getNome).toList()
		);
	}

    @Test
	public void deve_remover_um_desejo_pelo_id() throws Exception {
		Desejo desejo = new DesejoBuilder().construir();
		desejoRepository.saveAll(Arrays.asList(desejo));

		this.mockMvc
				.perform(delete("/api/v1/desejos/" + desejo.getId()))
				.andExpect(status().isOk());

		List<Desejo> desejoRetornados = desejoRepository.findByNomeContainingIgnoreCase(desejo.getNome());

		assertThat(desejoRetornados).isEmpty();
	};
    @Test
	void deve_buscar_um_desejo_pelo_id() throws Exception {
		Desejo desejo = new DesejoBuilder().construir();
		desejoRepository.save(desejo);
		
		MvcResult mvcResult = 
		mockMvc.perform(get("/api/v1/desejos/" + desejo.getId()))
		.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);

		String content = mvcResult.getResponse().getContentAsString();
		DesejoResponseDTO desejoDTO = JsonUtil.mapFromJsonModuleJavaTime(content, DesejoResponseDTO.class);

		assertThat(desejo.getId()).isEqualTo(desejoDTO.getId());
	}

	@Test
	void deve_retornar_uma_lista_de_todos_os_desejos() throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, Exception{
		cadastrarDezDesejos();
		int quantidadeEsperada = 10;

		DesejoResponseDTO[] desejoResponseDTOs = JsonUtil.mapFromJsonModuleJavaTime(
			this.mockMvc.perform(get("/api/v1/desejos")).andReturn().getResponse().getContentAsString(),DesejoResponseDTO[].class );

		assertThat(desejoResponseDTOs).hasSize(quantidadeEsperada);
	}

	private void cadastrarDezDesejos() throws Exception{
		for (int i = 0; i < 10; i++) {
			desejoRepository.save(new DesejoBuilder().construir());			
		}
	}

	@Test
	void deve_retornar_uma_desejo_alterado() throws Exception{
		
		Desejo desejo = new DesejoBuilder().construir();
		desejoRepository.save(desejo);
		String nome = "Video Game";
		String descricao = "PlayStation 1";
		double valor = 30;
		DesejoRequestDTO desejoRequestDTO = new DesejoRequestDTO(nome, descricao, valor);
		String nomeEsperado = "Copo da Stanley";
		desejoRequestDTO.setNome(nomeEsperado);

		this.mockMvc.perform(put("/api/v1/desejos/" + desejo.getId()).content(JsonUtil.toJson(desejoRequestDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		Iterable<Desejo> desejosEncontrados = desejoRepository.findAll();
		assertThat(desejosEncontrados).extracting(Desejo::getNome).containsOnly(nomeEsperado);
		
	}
}
