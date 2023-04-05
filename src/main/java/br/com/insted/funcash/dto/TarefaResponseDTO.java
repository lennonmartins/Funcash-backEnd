package br.com.insted.funcash.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String horaLimite;
    private LocalDate dataLimite;
    private LocalDateTime dataDeCriacao;
    private double valor;
    private String nome;

    public TarefaResponseDTO (Tarefa tarefa){
        this.horaLimite = tarefa.getHoraLimite();
        this.dataLimite = tarefa.getDataLimite();
        this.dataDeCriacao = tarefa.getDataDeCriacao();
        this.valor = tarefa.getValor();
        this.nome = tarefa.getNome();
    }
}
