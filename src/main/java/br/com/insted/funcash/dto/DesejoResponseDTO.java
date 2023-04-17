package br.com.insted.funcash.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesejoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private double valor;
}
