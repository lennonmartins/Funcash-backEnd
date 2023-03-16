package br.com.insted.funcash.dto;

import br.com.insted.funcash.models.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequestDTO {
    private double hora_limite;
    private String data_limite;
    private double moeda;
    private String nome;

    public TarefaRequestDTO(Tarefa tarefa){
        this.hora_limite = tarefa.getHora_limite();
        this.data_limite = tarefa.getData_limite();
        this.moeda = tarefa.getMoeda();
        this.nome = tarefa.getNome();
    }
}
