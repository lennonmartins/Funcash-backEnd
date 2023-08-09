package br.com.insted.funcash.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import br.com.insted.funcash.builders.CriancaBuilder;
import br.com.insted.funcash.builders.CriancaRequestDTOBuilder;
import br.com.insted.funcash.builders.ResponsavelBuilder;
import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.repository.CriancaRepository;
import br.com.insted.funcash.repository.ResponsavelRepository;
import br.com.insted.funcash.utils.JsonUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class CriancaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CriancaRepository criancaRepository;

	@Autowired
	private ResponsavelRepository responsavelRepository;
    
    @BeforeEach
	@AfterEach
	public void deleteDados() {
		criancaRepository.deleteAll();

	};

    @Test
	public void deve_incluir_uma_crianca() throws Exception  {
		int quantitadeEsperado = 1;
		Responsavel responsavel = new ResponsavelBuilder().construir();
		responsavelRepository.save(responsavel);
		CriancaRequestDTO criancaRequestDTO = new CriancaRequestDTOBuilder().comResponsavel(responsavel.getId()).construir();

		mockMvc.perform(post("/api/v1/criancas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(criancaRequestDTO)))
				.andExpect(status().isCreated());

		List<Crianca> criancaRetornados = criancaRepository.findByNomeContainingIgnoreCase(criancaRequestDTO.getNome());
		Assertions.assertThat(criancaRetornados.size()).isEqualTo(quantitadeEsperado);
		Assertions.assertThat(criancaRetornados.stream().map(Crianca::getNome).toList()).contains(criancaRequestDTO.getNome());

	}

	@Test
	void deve_buscar_uma_crianca_pelo_id() throws Exception {
		Crianca crianca = new CriancaBuilder().construir();
		criancaRepository.save(crianca);
		
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/criancas/" + crianca.getId())).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);

		String content = mvcResult.getResponse().getContentAsString();
		CriancaResponseDTO criancaDTO = JsonUtil.mapFromJsonModuleJavaTime(content, CriancaResponseDTO.class);

		Assertions.assertThat(crianca.getId()).isEqualTo(criancaDTO.getId());
	}	

    @Test
	public void deve_remover_uma_crianca_pelo_id() throws Exception {
		Crianca crianca = new CriancaBuilder().construir();
		criancaRepository.saveAll(Arrays.asList(crianca));

		this.mockMvc
				.perform(delete("/api/v1/criancas/" + crianca.getId()))
				.andExpect(status().isOk());

		List<Crianca> criancaRetornados = criancaRepository.findByNomeContainingIgnoreCase(crianca.getNome());

		assertThat(criancaRetornados).isEmpty();
	}

	@Test
	void deve_retornar_uma_crinca_atualizada() throws Exception{
		Crianca crianca = new CriancaBuilder().construir();
		criancaRepository.save(crianca);
		String nomeDaCriancaEsperado = "Luiza Teste";
		CriancaRequestDTO criancaRequestDTO = 
				new CriancaRequestDTOBuilder()
						.comNome(nomeDaCriancaEsperado)
						.construir();
		
		this.mockMvc
				.perform(put("/api/v1/criancas/" + crianca.getId())
				.content(JsonUtil.toJson(criancaRequestDTO))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

			Iterable<Crianca> criancasRetornadas = criancaRepository.findAll();
			assertThat(criancasRetornadas).extracting(Crianca::getNome).containsOnly(nomeDaCriancaEsperado);
	}

}
