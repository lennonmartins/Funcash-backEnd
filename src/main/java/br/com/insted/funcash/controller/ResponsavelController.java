package br.com.insted.funcash.controller;

import java.io.IOException;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import br.com.insted.funcash.dto.ResponsavelRequestDTO;
import br.com.insted.funcash.dto.ResponsavelResponseDTO;
import br.com.insted.funcash.repository.ResponsavelRepository;
import br.com.insted.funcash.service.ResponsavelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = {"/api/v1/responsavel"}, produces = {"application/json"})
public class ResponsavelController {
    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService){
        this.responsavelService = responsavelService;
    }
    
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Operation(summary = "Cadastrar um novo responsavel")
    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<ResponsavelResponseDTO> cadastrar(@RequestBody @Valid ResponsavelRequestDTO responsavelRequestDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(responsavelService.cadastrar(responsavelRequestDTO));
    }

    @Operation(summary ="Buscar um responsavel pelo seu id")
    @ApiResponse(responseCode = "200", description = "Retorna a responsavel solicitada" )
    @GetMapping(path= "/{id}")
    public ResponseEntity<ResponsavelResponseDTO> buscarPorId(@PathVariable Long id)  throws NameNotFoundException {
        return ResponseEntity.ok(responsavelService.buscarPorId(id));
    }
    
    @Operation(summary = "Buscar pelo id da criança")
    @GetMapping(path="/crianca/{id}")
    public ResponseEntity<Collection<ResponsavelResponseDTO>> buscarPelaCrianca(@PathVariable long id){
        return ResponseEntity.ok(responsavelService.buscarCriancaPeloResponsavel(id));
    }
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        responsavelRepository.deleteById(id);
    }
}
