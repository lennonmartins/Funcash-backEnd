package br.com.insted.funcash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    @Query("SELECT u FROM Usuario u JOIN u.responsavel r WHERE u.email = :email AND u.senha = :senha")
    Optional<Usuario> obterPorEmailESenha(@Param("email") String email, @Param("senha") String senha);
    //@Query("SELECT c FROM Crianca c JOIN c.responsavel r WHERE r.id = :idDoResponsavel")
    // @Query("SELECT r FROM Responsavel r WHERE r.email = :email AND r.senha = :senha")
    // Optional<Responsavel> encontrarPorEmailESenha(@Param("email") String email, @Param("senha") String senha);
}
