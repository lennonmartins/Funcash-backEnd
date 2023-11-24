package br.com.insted.funcash.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaResponseDTO {
    private Long id;
    private LocalDateTime horaLimite;
    private LocalDate dataLimite;
    private LocalDateTime dataDeCriacao;
    private double valor;
    private String nome;
    private String descricao;
    private long idDaCrianca;
}
