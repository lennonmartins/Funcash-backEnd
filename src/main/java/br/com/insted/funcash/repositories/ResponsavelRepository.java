package br.com.insted.funcash.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.insted.funcash.models.Responsavel;

public interface ResponsavelRepository extends CrudRepository<Responsavel, Long> {
    List<Responsavel> findByNomeContainingIgnoreCase(String nome);
}
