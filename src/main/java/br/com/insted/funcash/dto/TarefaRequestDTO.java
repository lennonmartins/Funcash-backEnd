package br.com.insted.funcash.dto;

import java.util.Date;

import br.com.insted.funcash.models.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequestDTO {
    private double hora_limite;
    private Date data_limite;
    private Date data_de_criacao;
    private double moeda;
    private String nome;

    public TarefaRequestDTO(Tarefa tarefa){
        this.hora_limite = tarefa.getHora_limite();
        this.data_limite = tarefa.getData_limite();
        this.data_de_criacao = tarefa.getData_de_criacao();
        this.moeda = tarefa.getMoeda();
        this.nome = tarefa.getNome();
    }
}
