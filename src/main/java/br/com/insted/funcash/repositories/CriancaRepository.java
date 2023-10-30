package br.com.insted.funcash.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.insted.funcash.models.Crianca;

public interface CriancaRepository extends CrudRepository<Crianca, Long> {
    List<Crianca> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT c FROM Crianca c JOIN c.responsavel r WHERE r.id = :idDoResponsavel")
    Collection<Crianca> findAllByResponsavel(Long idDoResponsavel);
}
