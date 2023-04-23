package br.com.insted.funcash.models;

import org.junit.jupiter.api.BeforeEach;

import br.com.insted.funcash.builders.ResponsavelBuilder;
import br.com.insted.funcash.utils.ArquivoUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
public class ResponsavelTest {
    private byte[] icone;

    @BeforeEach
    public void carregarIcone() throws IOException {
        this.icone = ArquivoUtils
                .abrirArquivo("src\\test\\java\\br\\com\\insted\\funcash\\icons\\Responsavel_Foto.svg");

    }

    @Test
    public void deve_criar_um_responsavel() throws FileNotFoundException, IOException, Exception {
        //Arrange
        String nome = "charmayanne ";
        String email = "isso@gmail.com";
        String cpf = "0098980";
        LocalDate dataDeNascimentoResponsavel = LocalDate.of(2023, 4, 10);
        Genero genero = Genero.MASCULINO;
        String senha = "123";
        byte[] foto = this.icone;

        //Action
        Responsavel responsavel = new ResponsavelBuilder().construir();

        //Assert
        Assertions.assertEquals(nome, responsavel.getNome());
        Assertions.assertEquals(email, responsavel.getEmail());
        Assertions.assertEquals(cpf, responsavel.getCpf());
        Assertions.assertEquals(dataDeNascimentoResponsavel, responsavel.getDataDeNascimentoResponsavel());
        Assertions.assertEquals(genero, responsavel.getGenero());
        Assertions.assertEquals(senha, responsavel.getSenha());
        Assertions.assertEquals(Arrays.toString(foto), Arrays.toString(responsavel.getFoto()));
    }

}
