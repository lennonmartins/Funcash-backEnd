package br.com.insted.funcash.builders;

import br.com.insted.funcash.models.Crianca;

public class CriancaBuilder {
    private String nome = "Flávio";
    private String email = "flavio@gmail";
    private String senha = "1234";
    private int idade = 20;

    public CriancaBuilder(){
       
    }
    public Crianca construir() {
        return new Crianca(email,senha,nome,idade);
    }

    public CriancaBuilder comEmail(String email){
        this.email = email;
        return this;
    }
    public CriancaBuilder comSenha(String senha){
        this.senha = senha;
        return this;
    }
    public CriancaBuilder comNome(String nome){
        this.nome = nome;
        return this;
    }
    public CriancaBuilder comIdade(int idade){
        this.idade = idade;
        return this;
    }
    
}
