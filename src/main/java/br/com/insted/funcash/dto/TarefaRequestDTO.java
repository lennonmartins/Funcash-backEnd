package br.com.insted.funcash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequestDTO {
    private String horaLimite;
    private String dataLimite;
    private double valor;
    private String nome;
}
