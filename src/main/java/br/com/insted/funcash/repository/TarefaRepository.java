package br.com.insted.funcash.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.insted.funcash.models.Tarefa;

public interface TarefaRepository extends CrudRepository<Tarefa, Long>{
    List<Tarefa> findByNomeContainingIgnoreCase(String nome);
    
    @Query("SELECT t FROM Tarefa t JOIN t.crianca c WHERE c.id = :idDaCrianca")
    Collection <Tarefa> findAllByCrianca(Long idDaCrianca);
}
