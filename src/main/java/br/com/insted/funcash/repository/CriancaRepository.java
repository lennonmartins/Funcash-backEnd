package br.com.insted.funcash.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.insted.funcash.models.Crianca;

public interface CriancaRepository extends CrudRepository<Crianca, Long> {
    List<Crianca> findByNomeContainingIgnoreCase(String nome);

    Crianca findFirstByIdOrderByIdDesc(Long id);
    
}
