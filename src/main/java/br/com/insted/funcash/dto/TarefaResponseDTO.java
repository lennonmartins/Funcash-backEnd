package br.com.insted.funcash.dto;

import java.sql.Time;
import java.util.Date;

import br.com.insted.funcash.models.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaResponseDTO {
    private Time hora_limite;
    private Date data_limite;
    private Date data_de_criacao;
    private double valor;
    private String nome;

    public TarefaResponseDTO (Tarefa tarefa){
        this.hora_limite = tarefa.getHora_limite();
        this.data_limite = tarefa.getData_limite();
        this.data_de_criacao = tarefa.getData_de_criacao();
        this.valor = tarefa.getValor();
        this.nome = tarefa.getNome();
    }
}
