package br.com.insted.funcash.dto;

import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriancaRequestDTO {

    private String dataDeNascimento;
    private String email;
    private String senha;
    private double saldo;
    private String nome;
    private String apelido;
    private Genero genero;

    public CriancaRequestDTO(Crianca crianca) {
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
        this.nome = nome;
        this.apelido = apelido;
        this.genero = genero;
    }

}
