package br.com.insted.funcash.builders;

import java.time.LocalDate;

import br.com.insted.funcash.models.Genero;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.models.Usuario;

public class ResponsavelBuilder {
    private String nome = "Charmayanne";
    private String email = "charmayanne@gmail";
    private String cpf = "123456";
    private LocalDate dataDeNascimentoResponsavel = LocalDate.of(2009, 07, 19);
    private Genero genero = Genero.FEMININO;
    private String senha = "1234567";
    private String foto = Image.getBytes();
    private Usuario usuario = new Usuario(email, senha);

    public Responsavel construir() throws Exception {
        return new Responsavel(usuario, nome, cpf, dataDeNascimentoResponsavel, genero, foto);
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

    public ResponsavelBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }
}
