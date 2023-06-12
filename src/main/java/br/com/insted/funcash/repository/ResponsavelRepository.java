package br.com.insted.funcash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.insted.funcash.models.Responsavel;

public interface ResponsavelRepository extends CrudRepository<Responsavel, Long> {
    List<Responsavel> findByNomeContainingIgnoreCase(String nome);

    Responsavel findFirstByIdOrderByIdDesc(Long id);

    @Query("SELECT r FROM Responsavel WHERE r.email = :email AND r.senha = :senha")
    Responsavel encontrarPorEmailESenha(@Param("email") String email, @Param("senha") String senha);
}
