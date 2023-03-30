package br.com.insted.funcash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;



@RestController
@RequestMapping(path = "/api/v1/desejo")
public class DesejoController {
    @Autowired
    private DesejoService desejoService;

    @Autowired
    private DesejoRepository desejoRepository;

    @Autowired
    private DesejoMapper desejoMapper;

    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<DesejoResponseDTO> cadastrarDesejo(@RequestBody @Valid DesejoRequestDTO desejoRequestDTO){
        Desejo desejo = desejoMapper.desejoRequestDesejo(desejoRequestDTO);
        DesejoResponseDTO desejoCadastrado = new DesejoResponseDTO(desejoRepository.save(desejo));
        return ResponseEntity.status(HttpStatus.CREATED).body(desejoCadastrado);
    }
    @Operation(summary ="Buscar uma desejo pelo seu id")
    @ApiResponse(responseCode = "200", description = "Retorna a criança solicitada" )
    @GetMapping(path= "/{id}")
    public ResponseEntity<DesejoResponseDTO> buscarPorId(@PathVariable Long id)  throws NameNotFoundException {
        return ResponseEntity.ok(desejoService.buscarPorId(id));
    }
    
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        desejoRepository.deleteById(id);
    }
}
