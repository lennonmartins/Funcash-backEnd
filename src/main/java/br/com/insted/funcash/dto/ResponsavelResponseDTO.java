package br.com.insted.funcash.dto;

import java.time.LocalDate;

import br.com.insted.funcash.models.Genero;
import br.com.insted.funcash.models.Responsavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsavelResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataDeNascimentoResponsavel;
    private Genero genero;
    private String senha;

    public ResponsavelResponseDTO(Responsavel responsavel){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataDeNascimentoResponsavel = dataDeNascimentoResponsavel;
        this.genero = genero;
        this.senha = senha;
    }
}
