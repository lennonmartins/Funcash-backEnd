package br.com.insted.funcash.dto;

import java.util.Date;

import br.com.insted.funcash.models.Crianca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriancaRequestDTO {

    private Date dataDeNascimento;
    private String email;
    private String senha;
    private double saldo;
    private String nome;
    private String apelido;

    public CriancaRequestDTO(Crianca crianca) {
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
        this.nome = nome;
        this.apelido = apelido;
    }

}
