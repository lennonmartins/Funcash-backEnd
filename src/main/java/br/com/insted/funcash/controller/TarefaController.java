package br.com.insted.funcash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.dto.TarefaResponseDTO;
import br.com.insted.funcash.mappers.TarefaMapper;
import br.com.insted.funcash.models.Tarefa;
import br.com.insted.funcash.repository.TarefaRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/tarefa")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepository;

    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<TarefaResponseDTO> cadastrar(@RequestBody TarefaRequestDTO tarefaRequestDTO) {
        Tarefa tarefa =TarefaMapper.toTarefa(tarefaRequestDTO);
        TarefaResponseDTO tarefaCadastrado = new TarefaResponseDTO(tarefaRepository.save(tarefa));
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCadastrado);
    }
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }
}
