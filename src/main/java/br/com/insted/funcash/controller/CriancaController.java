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

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.dto.CriancaResponseDTO;
import br.com.insted.funcash.mappers.CriancaMapper;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.repository.CriancaRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/crianca")
public class CriancaController {
    
    @Autowired
    private CriancaRepository criancaRepository;

    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<CriancaResponseDTO> cadastrarCrianca(@RequestBody CriancaRequestDTO criancaRequestDTO) {
        Crianca crianca = CriancaMapper.toCrianca(criancaRequestDTO);
        CriancaResponseDTO criancaCadastrado = new CriancaResponseDTO(criancaRepository.save(crianca));
        return ResponseEntity.status(HttpStatus.CREATED).body(criancaCadastrado);
    }
    
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        criancaRepository.deleteById(id);
    }
}
