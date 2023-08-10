package br.com.insted.funcash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.insted.funcash.models.Responsavel;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    List<Responsavel> findByNomeContainingIgnoreCase(String nome);

    Responsavel findFirstByIdOrderByIdDesc(Long id);
}
