package br.com.insted.funcash.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.insted.funcash.builders.CriancaBuilder;

@SpringBootTest
public class CriancaTest {
    
    @Test
    void deve_salvar_uma_crianca_com_genero() throws ParseException{
        Genero genero =  Genero.FEMININO;

        Crianca crianca = new CriancaBuilder().comGenero(genero).construir();

        assertEquals(genero, crianca.getGenero());
    }
}
