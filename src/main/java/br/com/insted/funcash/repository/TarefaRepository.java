package br.com.insted.funcash.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.insted.funcash.models.Tarefa;

public interface TarefaRepository extends CrudRepository<Tarefa, Long>{
    List<Tarefa> findByNomeContainingIgnoreCase(String nome);
}
