package br.com.insted.funcash.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.insted.funcash.models.Responsavel;

public interface ResponsavelRepository extends CrudRepository<Responsavel, Long> {
    List<Responsavel> findByNomeContainingIgnoreCase(String nome);

    Responsavel findFirstByIdOrderByIdDesc(Long id);
}
