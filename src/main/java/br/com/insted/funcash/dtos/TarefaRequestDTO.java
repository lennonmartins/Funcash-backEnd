package br.com.insted.funcash.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarefaRequestDTO {
    private String horaLimite;
    private String dataLimite;
    private double valor;
    private String nome;
    private String descricao;
    private Long idDaCrianca;

    public TarefaRequestDTO(String horaLimite, String dataLimite, double valor, String nome, String descricao, Long idDaCrianca) {
        this.horaLimite = horaLimite;
        this.dataLimite = dataLimite;
        this.valor = valor;
        this.nome = nome;
        this.idDaCrianca = idDaCrianca;
        this.descricao = descricao;
    }
}
