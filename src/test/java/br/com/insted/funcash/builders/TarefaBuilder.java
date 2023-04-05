package br.com.insted.funcash.builders;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import br.com.insted.funcash.models.Tarefa;

public class TarefaBuilder {
    private String horaLimiteEmString = "19:30:00";
    private LocalDate dataLimite =  LocalDate.of(2023, 04, 05);
    private double moeda = 30;
    private String nome = "Tirar lixo";


    public TarefaBuilder(){
       
    }

    public Tarefa construir(){
        return new Tarefa(horaLimiteEmString,dataLimite,moeda,nome);
    }

    private Date obterData(String data_limite_em_string) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.parse(data_limite_em_string);
    }
    
    // private String obterHora(String horaLimiteEmString) throws ParseException{
    //     SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    //     DateFormat horaformatada = DateFormat.getDateTimeInstance();
    //     return horaformatada.format(formato.parse(horaLimiteEmString).getTime());
    // }

    private Time obterHora(String horaLimiteEmString){
        return Time.valueOf(horaLimiteEmString);
    }

    public TarefaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }
}
