package br.com.insted.funcash.builders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.insted.funcash.models.Crianca;

public class CriancaBuilder {

    private String nome = "Flávio";
    private String email = "flavio@gmail";
    private String senha = "1234";
    private double saldo = 100.00;
    private String apelido = "toinho";
    private String dataDeNascimentoemString = "20/03/2023";
    private Date dataDeNascimento;

    public CriancaBuilder() {

    }

    public Crianca construir() throws ParseException {
        return new Crianca(obterData(dataDeNascimentoemString), email, senha, saldo, nome, apelido);
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

    public CriancaBuilder comDataDeNascimento(String dataDeNascimento) throws ParseException {
        this.dataDeNascimento = obterData(dataDeNascimento);
        return this;
    }

    private Date obterData(String dataEmString) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.parse(dataEmString);
    }
}
