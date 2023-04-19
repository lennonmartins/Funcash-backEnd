package br.com.insted.funcash.models;

import java.time.LocalDate;

import br.com.insted.funcash.utils.ArquivoUtils;

@SpringBootTest
public class ResponsavelTest {
    private byte[] icone;

    @BeforeEach
    public void carregarIcone() throws IOException {
        this.icone = ArquivoUtils.abrirArquivo("src\\test\\java\\br\\com\\insted\\funcash\\icons\\Responsavel_Foto.svg");
    }

    @Test 
    public void deve_criar_um_tipo() throws FileNotFoundException, IOException {
        // Arrange
        String nome = "Water";
        String email = "#4592c4";
        String cpf = "";
        LocalDate data
        byte[] foto = this.icone;

        // Action
        Tipo tipo = new TipoBuilder().construir();

        // Assert
        Assertions.assertEquals(nome, tipo.getNome());
        Assertions.assertEquals(cor, tipo.getCor());
        Assertions.assertEquals(Arrays.toString(simbolo), Arrays.toString(tipo.getSimbolo()));
    }
}
