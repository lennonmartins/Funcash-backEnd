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
    private double valor;

    public DesejoResponseDTO(Desejo desejo){
        this.id = desejo.getId();
        this.nome = desejo.getNome();
        this.descricao = desejo.getDescricao();
        this.valor = desejo.getValor();
    }

}
