package br.com.insted.funcash.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DesejoRequestDTO {
    private String nome;
    private String descricao;
    private double valor;

    public DesejoRequestDTO(String nome, String descricao, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }
}
