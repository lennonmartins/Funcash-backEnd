// package br.com.insted.funcash.models;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.text.ParseException;
// import java.time.LocalDate;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;

// import br.com.insted.funcash.builders.CriancaBuilder;

// @SpringBootTest
// public class CriancaTest {
    
//     @Test
//     void deve_criar_uma_crianca_com_genero() throws Exception{
//         Genero genero =  Genero.FEMININO;
        
//         Crianca crianca = new CriancaBuilder().comGenero(genero).construir();

//         assertEquals(genero, crianca.getGenero());
//     }

//     @Test
//     void deve_criar_uma_crianca_com_data_de_nascimento() throws ParseException, Exception{
//         LocalDate dataDeNascimentoEsperada = LocalDate.of(2010, 07, 19);

//         Crianca crianca = new CriancaBuilder().comDataDeNascimento(dataDeNascimentoEsperada).construir();

//         assertEquals(dataDeNascimentoEsperada, crianca.getDataDeNascimento());
//     }
// }
