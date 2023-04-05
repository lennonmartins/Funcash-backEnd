package br.com.insted.funcash.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
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
		String nome = "Cuidar do cachorro";
		String descricao = "trocar racao";
		double valor = 30;
		DesejoRequestDTO desejoRequestDTO = new DesejoRequestDTO(nome, descricao, valor);

		mockMvc.perform(post("/api/v1/desejos")
		.contentType(MediaType.APPLICATION_JSON)
		.content(JsonUtil.toJson(desejoRequestDTO)))
		.andExpect(status().isCreated());

		List<Desejo> desejoRetornados = desejoRepository.findByNomeContainingIgnoreCase(desejoRequestDTO.getNome());

		Assertions.assertThat(desejoRetornados.size()).isEqualTo(quantidadeEsperada);
		Assertions.assertThat(
			desejoRequestDTO.getNome()).isIn(desejoRetornados.stream().map(Desejo::getNome).toList()
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

		Assertions.assertThat(desejoRetornados).isEmpty();
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
		DesejoResponseDTO desejoDTO = JsonUtil.mapFomJson(content, DesejoResponseDTO.class);

		Assertions.assertThat(desejo.getId()).isEqualTo(desejoDTO.getId());
	}
}
