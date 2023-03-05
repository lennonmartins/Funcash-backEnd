package br.com.insted.funcash.controller;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.insted.funcash.builders.CriancaBuilder;
import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.repository.CriancaRepository;
import net.minidev.json.JSONUtil;
import static org.hamcrest.Matchers.containsString;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CrincaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CriancaRepository criancaRepository;
    
    @Test
	public void deve_cadastrar_uma_crianca() throws Exception {
		Crianca crianca = new CriancaBuilder().construir();
		String json = toJson(crianca);

		this.mockMvc
				.perform(post("/crianca").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());

		List<Crianca> criancaRetornados = criancaRepository.findByNomeContainingIgnoreCase(crianca.getNome());
		Assertions.assertThat(criancaRetornados.size()).isEqualTo(1);
		Assertions.assertThat(criancaRetornados.stream().map(Crianca::getNome).toList()).contains(crianca.getNome());

	}

    private String toJson(Crianca crianca) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(crianca);
		return json;
	};



}
