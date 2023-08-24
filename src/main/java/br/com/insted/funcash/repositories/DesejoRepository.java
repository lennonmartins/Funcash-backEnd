package br.com.insted.funcash.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.insted.funcash.models.Desejo;

public interface DesejoRepository extends CrudRepository<Desejo, Long> {
    List<Desejo>findByNomeContainingIgnoreCase(String nome);
}
