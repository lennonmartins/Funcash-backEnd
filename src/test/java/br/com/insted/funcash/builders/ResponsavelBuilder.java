package br.com.insted.funcash.builders;

import java.time.LocalDate;
import java.io.IOException;
import br.com.insted.funcash.models.Genero;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.utils.ArquivoUtils;

public class ResponsavelBuilder {
    private String nome = "Charmayanne";

    private String email = "charmayanne@gmail";

    private String cpf = "123456";

    private LocalDate dataDeNascimentoResponsavel = LocalDate.of(2009, 07, 19);

    private Genero genero = Genero.FEMININO;

    private String senha = "1234567";

    private byte[] foto;

    public ResponsavelBuilder()throws IOException {
        this.foto = ArquivoUtils.abrirArquivo("src\\test\\java\\br\\com\\insted\\funcash\\icons\\Responsavel_Foto.svg");
    }

    public Responsavel construir() throws Exception {
        return new Responsavel(nome, email, cpf, dataDeNascimentoResponsavel, genero, senha, foto);
    }

    public ResponsavelBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ResponsavelBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public ResponsavelBuilder comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ResponsavelBuilder comdataDeNascimentoResponsavel(LocalDate dataDeNascimentoResponsavel) {
        this.dataDeNascimentoResponsavel = dataDeNascimentoResponsavel;
        return this;
    }

    public ResponsavelBuilder comGenero(Genero genero) {
        this.genero = genero;
        return this;
    }

    public ResponsavelBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }
}
