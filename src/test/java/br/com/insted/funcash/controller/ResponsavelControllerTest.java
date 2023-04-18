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

import br.com.insted.funcash.builders.ResponsavelBuilder;
import br.com.insted.funcash.dto.ResponsavelRequestDTO;
import br.com.insted.funcash.dto.ResponsavelResponseDTO;
import br.com.insted.funcash.models.Genero;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.repository.ResponsavelRepository;
import br.com.insted.funcash.utils.JsonUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class ResponsavelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @BeforeEach
    @AfterEach
    public void deleteDados() {
        responsavelRepository.deleteAll();

    }

    @Test
    public void deve_incluir_um_responsavel() throws Exception {
        int quantitadeEsperado = 1;
        String nome = "Charmayanne";
        String email = "charmayanne@gmail";
        String cpf = "123456";
        String dataDeNascimentoResponsavelString = "2023-02-21";
        Genero genero = Genero.FEMININO;
        String senha = "1234567";
        ResponsavelRequestDTO responsavelRequestDTO = new ResponsavelRequestDTO(nome, email, cpf,dataDeNascimentoResponsavelString,genero,senha );

        mockMvc.perform(post("/api/v1/responsavel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(responsavelRequestDTO)))
                .andExpect(status().isCreated());

        List<Responsavel> responsavelRetornados = responsavelRepository.findByNomeContainingIgnoreCase(responsavelRequestDTO.getNome());
        Assertions.assertThat(responsavelRetornados.size()).isEqualTo(quantitadeEsperado);
        Assertions.assertThat(responsavelRetornados.stream().map(Responsavel::getNome).toList())
                .contains(responsavelRequestDTO.getNome());

    }

    @Test
    void deve_buscar_um_responsavel_pelo_id() throws Exception {
        Responsavel responsavel = new ResponsavelBuilder().construir();
        responsavelRepository.save(responsavel);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/responsavel/" + responsavel.getId())).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        ResponsavelResponseDTO responsavelDTO = JsonUtil.mapFromJsonModuleJavaTime(content, ResponsavelResponseDTO.class);

        Assertions.assertThat(responsavel.getId()).isEqualTo(responsavelDTO.getId());
    }

    @Test
    public void deve_remover_um_responsavel_pelo_id() throws Exception {
        Responsavel responsavel = new ResponsavelBuilder().construir();
        responsavelRepository.saveAll(Arrays.asList(responsavel));

        this.mockMvc
                .perform(delete("/api/v1/responsavel/" + responsavel.getId()))
                .andExpect(status().isOk());

        List<Responsavel> responsavelRetornados = responsavelRepository.findByNomeContainingIgnoreCase(responsavel.getNome());

        Assertions.assertThat(responsavelRetornados).isEmpty();
    }
}
