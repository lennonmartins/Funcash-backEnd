package br.com.insted.funcash.dto;

import br.com.insted.funcash.models.Desejo;
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
    private int valor;

    public DesejoResponseDTO(Desejo desejo){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

}
