package br.com.insted.funcash.controllers;

import java.util.Collection;

import javax.validation.Valid;

import br.com.insted.funcash.dtos.TarefaResponsePageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.insted.funcash.dtos.TarefaRequestDTO;
import br.com.insted.funcash.dtos.TarefaResponseDTO;
import br.com.insted.funcash.services.TarefaService;
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
    @PreAuthorize("hasRole('RESPONSAVEL')")
    public ResponseEntity<TarefaResponseDTO> cadastrarTarefa(@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.cadastrar(tarefaRequestDTO));
    }

    @Operation(summary = "Deleta uma tarefa")
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('RESPONSAVEL')")
    public void remover(@PathVariable Long id) {
        tarefaService.deletar(id);
    }

    @Operation(summary ="Buscar uma lista das tarefas")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas cadastradas")
    @GetMapping
    public ResponseEntity<Collection<TarefaResponseDTO>> buscarTodas(){
        return ResponseEntity.ok(tarefaService.buscarTodas());
    }

    @Operation(summary = "Buscar uma tarefa pelo seu id")
    @ApiResponse(responseCode = "200")
    @GetMapping(path = "/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar uma tarefa")
    @ApiResponse(responseCode = "200")
    @PreAuthorize("hasRole('RESPONSAVEL')")
    @PutMapping(path="/{id}", consumes = {"application/json"})
    public ResponseEntity<TarefaResponseDTO> alteraTarefa(@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO, @PathVariable Long id){
        return ResponseEntity.ok(tarefaService.alterar(tarefaRequestDTO, id));
    }

    @Operation(summary = "Buscar tarefas pelo id da criança")
    @GetMapping(path="/crianca/{id}/tarefas")
    public ResponseEntity<Collection<TarefaResponseDTO>> buscarPeloIdCrianca(@PathVariable long id){
        return ResponseEntity.ok(tarefaService.buscarTarefasPelaCrianca(id));
    }

    @Operation(summary = "Retorna uma lista de tarefas ordenadas e filtradas baseada nos parametros da busca")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas encontradas e ordenadas")
    @GetMapping
    public ResponseEntity<TarefaResponsePageDTO> buscarPeloTitulo(
            @RequestParam(required = false, name = "pagina", defaultValue = "0") int pagina,
            @RequestParam(required = false, name = "quantidade", defaultValue = "10") int quantidade,
            @RequestParam(required = false, name = "fatorOrdenacao", defaultValue = "horaLimite") String fatorOrdenacao,
            @RequestParam(required = false, name = "direcao", defaultValue = "ASC") String direcao,
            @RequestParam(required = false, name = "titulo") String titulo
    ){
        return ResponseEntity.ok(tarefaService.buscarPeloTitulo(pagina, quantidade, fatorOrdenacao,direcao,titulo));
    }
}


