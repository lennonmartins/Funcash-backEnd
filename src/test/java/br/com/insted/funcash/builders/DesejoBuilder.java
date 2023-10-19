package br.com.insted.funcash.builders;

import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Desejo;

public class DesejoBuilder {
    private String nome = "Cuidar do cachorro";
    private String descricao = "trocar racao";
    private double valor = 30;
    private Crianca crianca;

    public DesejoBuilder(){

    }

    public Desejo construir() throws Exception {
        return new Desejo(nome,descricao,valor,crianca);
    }

    public DesejoBuilder comNome( String nome ) {
        this.nome = nome;
        return this;
    }

    public DesejoBuilder comDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public DesejoBuilder comValor(double valor) {
        this.valor = valor;
        return this;
    }
}

   
   