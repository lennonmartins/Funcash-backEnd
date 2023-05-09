package br.com.insted.funcash.repository;

import java.util.List;
import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.insted.funcash.models.Responsavel;

public interface ResponsavelRepository extends CrudRepository<Responsavel, Long> {
    List<Responsavel> findByNomeContainingIgnoreCase(String nome);

    Responsavel findFirstByIdOrderByIdDesc(Long id);
    
    @Query("SELECT t FROM Tarefa t JOIN t.crianca c WHERE c.id = :idDaCrianca")
    Collection <Responsavel> findAllByCrianca(Long idDaCrianca);
}
