package br.com.insted.funcash.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.insted.funcash.models.Desejo;

public interface DesejoRepository extends CrudRepository<Desejo, Long> {
    List<Desejo>findByNomeContainingIgnoreCase(String nome);
}
