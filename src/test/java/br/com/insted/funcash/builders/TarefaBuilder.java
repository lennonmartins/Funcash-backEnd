package br.com.insted.funcash.builders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.insted.funcash.models.Tarefa;

public class TarefaBuilder {
    private double hora_limite = 50;
    private String data_limite_sting = "20/03/2023";
    private String data_de_criacao_string = "10/03/2023";
    private double moeda = 30;
    private String nome = "Flávio";
    private Date data_limite;
    private Date data_de_criacao;

    public TarefaBuilder(){
       
    }
    public Tarefa construir() throws ParseException {
        return new Tarefa(hora_limite,obterData(data_limite_sting),obterData(data_de_criacao_string),moeda,nome);
    }
    private Date obterData(String data_limite_em_string) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.parse(data_limite_em_string);
    }
}
