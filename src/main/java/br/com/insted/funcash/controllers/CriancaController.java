package br.com.insted.funcash.controllers;

import java.util.Collection;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.insted.funcash.dtos.CriancaRequestDTO;
import br.com.insted.funcash.dtos.CriancaResponseDTO;
import br.com.insted.funcash.repositories.CriancaRepository;
import br.com.insted.funcash.services.CriancaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600, allowCredentials = "true")
@RequestMapping(path = {"/api/v1/criancas"}, produces = {"application/json"})
public class CriancaController {
    private final CriancaService criancaService;

    public CriancaController(CriancaService criancaService){
        this.criancaService = criancaService;
    }
    
    @Autowired
    private CriancaRepository criancaRepository;

    @Operation(summary = "Cadastrar uma nova crianca")
    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = {"application/json"})
    @PreAuthorize("hasRole('RESPONSAVEL')")
    public ResponseEntity<CriancaResponseDTO> cadastrarCrianca(@RequestBody @Valid CriancaRequestDTO criancaRequestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(criancaService.cadastrar(criancaRequestDTO));
    }

    @Operation(summary ="Buscar uma criança pelo seu id")
    @ApiResponse(responseCode = "200", description = "Retorna a criança solicitada" )
    @GetMapping(path= "/{id}")
    public ResponseEntity<CriancaResponseDTO> buscarPorId(@PathVariable Long id)  throws NameNotFoundException {
        return ResponseEntity.ok(criancaService.buscarPorId(id));
    }

    @PreAuthorize("hasRole('RESPONSAVEL')")
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        criancaRepository.deleteById(id);
    }
    
    @PreAuthorize("hasRole('RESPONSAVEL')")
    @Operation(summary = "Buscar lista de todas as crianças")
    @ApiResponse(responseCode = "200", description = "Retorna uma lista de criancas cadastradas")
    @GetMapping
    public ResponseEntity<Collection<CriancaResponseDTO>> buscarTodasAsCriancas(){
        return ResponseEntity.ok(criancaService.buscarTodas());
    }

    @Operation(summary = "Busca uma lista de crianca pelo id do responsavel")
    @ApiResponse(responseCode = "200")
    @GetMapping(path = "/{id}/criancas")
    public ResponseEntity<Collection<CriancaResponseDTO>> buscarTodasPeloIdDoResponsavel(@PathVariable long id){
        return ResponseEntity.ok(criancaService.buscarCriancasPeloResponsavel(id));
    }

    @Operation(summary = "Altera os dados de uma crianca cadastarda")
    @ApiResponse(responseCode = "200")
    @PutMapping(path = "/{id}")
    public ResponseEntity<CriancaResponseDTO> alterarDadosDaCrianca(@RequestBody @Valid CriancaRequestDTO criancaRequestDTO, @PathVariable Long id ){
        return ResponseEntity.ok(criancaService.alterar(criancaRequestDTO, id));
    }
}
