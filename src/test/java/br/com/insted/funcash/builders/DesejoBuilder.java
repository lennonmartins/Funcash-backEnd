package br.com.insted.funcash.builders;

import br.com.insted.funcash.models.Desejo;
import java.text.ParseException;

public class DesejoBuilder {
    private String nome = "Cuidar do cachorro";
    private String descricao = "trocar ração do cachorro";
    private int valor = 30;

    public DesejoBuilder(){

    }

    public Desejo construir() throws ParseException {
        return new Desejo(nome,descricao,valor);
    }
}

   
   