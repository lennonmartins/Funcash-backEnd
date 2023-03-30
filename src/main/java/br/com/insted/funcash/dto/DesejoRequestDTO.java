package br.com.insted.funcash.dto;

import br.com.insted.funcash.models.Desejo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesejoRequestDTO {
    private String nome;
    private String descricao;
    private int valor;

    public DesejoRequestDTO(Desejo desejo){
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }
}
