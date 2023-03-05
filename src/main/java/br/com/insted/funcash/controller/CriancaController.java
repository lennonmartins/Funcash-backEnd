package br.com.insted.funcash.controller;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.repository.CriancaRepository;

@RestController
@RequestMapping(path = "/crianca")
public class CriancaController {
    
    @Autowired
    private CriancaRepository criancaRepository;

    @PostMapping
    public ResponseEntity<Crianca> cadastrar(@RequestBody Crianca crianca) {
        Crianca criancaCadastrado = criancaRepository.save(crianca);
        return ResponseEntity.status(HttpStatus.CREATED).body(criancaCadastrado);
    }
}
