package br.com.insted.funcash.controller;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.insted.funcash.dto.DesejoRequestDTO;
import br.com.insted.funcash.dto.DesejoResponseDTO;
import br.com.insted.funcash.mappers.DesejoMapper;
import br.com.insted.funcash.models.Desejo;
import br.com.insted.funcash.repository.DesejoRepository;
import br.com.insted.funcash.service.DesejoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



@RestController
@RequestMapping(path = {"/api/v1/desejos"}, produces = {"application/json"})
public class DesejoController {
    private final DesejoService desejoService;

    public DesejoController( DesejoService desejoService){
        this.desejoService = desejoService;
    }

    @Autowired
    private DesejoRepository desejoRepository;

    @Autowired
    private DesejoMapper desejoMapper;

    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<DesejoResponseDTO> cadastrarDesejo(@RequestBody @Valid DesejoRequestDTO desejoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(desejoService.cadastrar(desejoRequestDTO));
    }
    @Operation(summary ="Buscar uma desejo pelo seu id")
    @ApiResponse(responseCode = "200", description = "Retorna o desejo solicitada" )
    @GetMapping(path= "/{id}")
    public ResponseEntity<DesejoResponseDTO> buscarPorId(@PathVariable Long id)  throws NameNotFoundException {
        return ResponseEntity.ok(desejoService.buscarPorId(id));
    }
    
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        desejoRepository.deleteById(id);
    }
}
