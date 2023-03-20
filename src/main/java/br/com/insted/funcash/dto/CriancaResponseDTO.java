package br.com.insted.funcash.dto;

import java.util.Date;

import br.com.insted.funcash.models.Crianca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriancaResponseDTO {

    private Long id;
    
    private Date dataDeNascimento;
    
    private String email;
    
    private String senha;

    private double saldo;
    
    private String nome;

    private String apelido;

    public CriancaResponseDTO(Crianca crianca) {
        this.id = id;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
        this.nome = nome;
        this.apelido = apelido;
    }


   
}
