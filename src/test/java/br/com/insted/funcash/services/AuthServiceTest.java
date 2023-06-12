package br.com.insted.funcash.services;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.repository.ResponsavelRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
@DataJpaTest
public class AuthServiceTest {
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Test
    public void testEncontrarPorEmailESenha() {
        // Dados de teste
        String email = "seu_email";
        String senha = "sua_senha";

        // Realiza a busca
        Optional<Responsavel> responsavelOptional = responsavelRepository.encontrarPorEmailESenha(email, senha);

        // Verifica se o resultado foi encontrado
        assertTrue(responsavelOptional.isPresent(), "Usuário responsável não encontrado");

        // Obtém o objeto Responsavel do resultado
        Responsavel responsavel = responsavelOptional.get();

        // Verifique se o email e a senha correspondem aos dados de teste
        assertEquals(email, responsavel.getEmail(), "Email não corresponde");
        assertEquals(senha, responsavel.getSenha(), "Senha não corresponde");
    }
}
