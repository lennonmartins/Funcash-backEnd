package br.com.insted.funcash.builders;

import br.com.insted.funcash.models.Tarefa;

public class TarefaBuilder {
    private double hora_limite = 50;
    private String data_limite = "xx/xx/xxxx";
    private double moeda = 30;
    private String nome = "Flávio";

    public TarefaBuilder(){
       
    }
    public Tarefa construir() {
        return new Tarefa(hora_limite,data_limite,moeda,nome);
    }
}
