package br.com.insted.funcash.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.insted.funcash.models.Tarefa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends PagingAndSortingRepository<Tarefa, Long> {
    List<Tarefa> findByTituloContainingIgnoreCase(String titulo);
    
    @Query("SELECT t FROM Tarefa t JOIN t.crianca c WHERE c.id = :idDaCrianca")
    Collection <Tarefa> findAllByCrianca(Long idDaCrianca);

    @Cacheable("tarefasPaginadas")
    Page<Tarefa> findByTituloContaining(String titulo, Pageable pageable);
}
