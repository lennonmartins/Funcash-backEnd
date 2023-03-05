package br.com.insted.funcash.builders;

import br.com.insted.funcash.models.Crianca;

public class CriancaBuilder {
    private String nome;
    private String email;
    private String senha;
    private int idade;

    public CriancaBuilder(){
        this.nome ="Flavio";
        this.email = "fkjskf@gmail.com";
        this.idade = 20;
        this.senha = "123";
    }

    public Crianca cadastrar(){
        return new Crianca(email,senha,nome,idade);
    }

    public Crianca construir() {
        return new Crianca(email,senha,nome,idade);
    }
}
