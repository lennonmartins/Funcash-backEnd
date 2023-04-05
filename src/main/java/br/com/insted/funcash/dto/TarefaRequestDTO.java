package br.com.insted.funcash.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

import br.com.insted.funcash.models.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequestDTO {
    private String horaLimite;
    private LocalDate dataLimite;
    private double valor;
    private String nome;

    public TarefaRequestDTO(Tarefa tarefa){
        this.horaLimite = tarefa.getHoraLimite();
        this.dataLimite = tarefa.getDataLimite();
        this.valor = tarefa.getValor();
        this.nome = tarefa.getNome();
    }
}
