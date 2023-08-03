package br.com.insted.funcash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.insted.funcash.models.Responsavel;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    List<Responsavel> findByNomeContainingIgnoreCase(String nome);

    Responsavel findFirstByIdOrderByIdDesc(Long id);

}
