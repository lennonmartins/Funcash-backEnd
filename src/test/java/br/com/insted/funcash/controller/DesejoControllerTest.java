package br.com.insted.funcash.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import br.com.insted.funcash.builders.DesejoBuilder;
import br.com.insted.funcash.models.Desejo;
import br.com.insted.funcash.repository.DesejoRepository;
@SpringBootTest
@AutoConfigureMockMvc
public class DesejoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DesejoRepository desejoRepository;
    
    @BeforeEach
	@AfterEach
	public void deleteDados() {
		desejoRepository.deleteAll();

	}

    @Test
	public void deve_incluir_uma_tarefa() throws Exception  {
		Desejo desejo = new DesejoBuilder().construir();;
		String json = toJson(desejo);

		this.mockMvc
        .perform(post("/api/v1/desejo").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				// .perform(post("/api/v1/desejo").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());

		List<Desejo> desejoRetornados = desejoRepository.findByNomeContainingIgnoreCase(desejo.getNome());
		Assertions.assertThat(desejoRetornados.size()).isEqualTo(1);
		Assertions.assertThat(desejoRetornados.stream().map(Desejo::getNome).toList()).contains(desejo.getNome());

	}

    private String toJson(Desejo desejo) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(desejo);
		return json;
	};
}
