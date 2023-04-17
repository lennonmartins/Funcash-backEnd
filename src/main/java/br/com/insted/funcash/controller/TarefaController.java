package br.com.insted.funcash.controller;

import javax.validation.Valid;

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
import br.com.insted.funcash.repository.TarefaRepository;
import br.com.insted.funcash.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = {"api/v1/tarefas"}, produces ={"application/json"})
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @Autowired
    private TarefaRepository tarefaRepository;

    @Operation(summary = "Cadastrar uma nova tarefa")
    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<TarefaResponseDTO> cadastrarTarefa(@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.cadastrar(tarefaRequestDTO));
    }

    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }
}
