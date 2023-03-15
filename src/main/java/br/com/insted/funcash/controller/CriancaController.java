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

import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.repository.CriancaRepository;

@RestController
@RequestMapping(path = "/crianca")
public class CriancaController {
    
    @Autowired
    private CriancaRepository criancaRepository;

    @PostMapping
    public ResponseEntity<Crianca> cadastrarCrianca(@RequestBody Crianca crianca) {
        Crianca criancaCadastrado = criancaRepository.save(crianca);
        return ResponseEntity.status(HttpStatus.CREATED).body(criancaCadastrado);
    }
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        criancaRepository.deleteById(id);
    }
}
