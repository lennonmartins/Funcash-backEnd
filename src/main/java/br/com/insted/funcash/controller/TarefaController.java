package br.com.insted.funcash.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.insted.funcash.dto.TarefaRequestDTO;
import br.com.insted.funcash.dto.TarefaResponseDTO;
import br.com.insted.funcash.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = {"api/v1/tarefas"}, produces ={"application/json"})
public class TarefaController {
    private final TarefaService tarefaService;
    
    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @Operation(summary = "Cadastrar uma nova tarefa")
    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<TarefaResponseDTO> cadastrarTarefa(@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.cadastrar(tarefaRequestDTO));
    }

    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        tarefaService.deletar(id);
    }

    @Operation(summary ="Buscar uma lista das tarefas")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas cadastradas")
    @GetMapping
    public ResponseEntity<Collection<TarefaResponseDTO>> buscarTodos(){
        return ResponseEntity.ok(tarefaService.buscarTodas());
    }

    @Operation(summary = "Buscar um tarefa pelo seu id")
    @ApiResponse(responseCode = "200")
    @GetMapping(path = "/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar uma tarefa")
    @ApiResponse(responseCode = "200")
    @PutMapping(path="/{id}", consumes = {"application/json"})
    public ResponseEntity<TarefaResponseDTO> alteraTarefa(@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO, @PathVariable Long id){
        return ResponseEntity.ok(tarefaService.alterar(tarefaRequestDTO, id));
    }

    @Operation(summary = "Buscar tarefas pelo id da criança")
    @GetMapping(path="/crianca/{id}")
    public ResponseEntity<Collection<TarefaResponseDTO>> buscarPeloIdCrianca(@PathVariable long id){
        return ResponseEntity.ok(tarefaService.buscarTarefasPelaCrianca(id));
    }
}


