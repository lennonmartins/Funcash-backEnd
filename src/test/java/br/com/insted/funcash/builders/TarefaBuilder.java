package br.com.insted.funcash.builders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Tarefa;

public class TarefaBuilder {
    private LocalTime horaLimite = LocalTime.of(19, 30, 0);
    private LocalDate dataLimite = LocalDate.of(2023, 04, 05);
    private LocalDateTime horaLimiteCompleta = LocalDateTime.of(dataLimite, horaLimite);
    private double moeda = 30;
    private String nome = "Tirar lixo";
    private Crianca crianca;

    public TarefaBuilder() {

    }

    public Tarefa construir() throws Exception {
        crianca = new CriancaBuilder().construir();
        return new Tarefa(horaLimiteCompleta, dataLimite, moeda, nome, crianca);
    }

    public TarefaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public TarefaBuilder comDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
        return this;
    }

    public TarefaBuilder comHoraLimite(LocalDate dataLimite, LocalTime horaLimite) {
        this.horaLimiteCompleta = LocalDateTime.of(dataLimite, horaLimite);
        return this;
    }

    public TarefaBuilder comCrianca(Crianca crianca) {
        this.crianca = crianca;
        return this;
    }
}
