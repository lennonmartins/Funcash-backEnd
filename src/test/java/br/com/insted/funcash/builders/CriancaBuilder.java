package br.com.insted.funcash.builders;

import java.time.LocalDate;

import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Genero;
import br.com.insted.funcash.models.Responsavel;

public class CriancaBuilder {

    private String nome = "Pluto";
    private String email = "pluto@gmail";
    private String senha = "1234";
    private double saldo = 100.00;
    private String apelido = "toinho";
    private LocalDate dataDeNascimento = LocalDate.of(2010, 07, 19);
    private Genero genero = Genero.MASCULINO;
    private String foto = Image.getBytes();
    private Responsavel responsavel;

    public CriancaBuilder() {

    }

    public Crianca construir() throws Exception {
        return new Crianca(dataDeNascimento, email, senha, saldo, nome, apelido, genero,foto, responsavel);
    }

    public CriancaBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public CriancaBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public CriancaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public CriancaBuilder comDataDeNascimento(LocalDate dataDeNascimento)  {
        this.dataDeNascimento = dataDeNascimento;
        return this;
    }

    public CriancaBuilder comGenero(Genero genero) {
        this.genero = genero;
        return this;
    }
}
