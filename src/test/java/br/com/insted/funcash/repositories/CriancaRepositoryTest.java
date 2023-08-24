package br.com.insted.funcash.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.insted.funcash.builders.CriancaBuilder;
import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.repositories.CriancaRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

@DataJpaTest
public class CriancaRepositoryTest {

    @Autowired
    private CriancaRepository criancaRepository; 
    
    @BeforeEach
    void setUp(){
        criancaRepository.deleteAll();
    }

    @Test
    void deve_salvar_uma_crianca_com_id() throws ParseException, Exception{
        Crianca crianca = new CriancaBuilder().construir();

        criancaRepository.save(crianca);

        assertThat(crianca.getId()).isNotNull();
    }


}
