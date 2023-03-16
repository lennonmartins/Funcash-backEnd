package br.com.insted.funcash.dto;

import br.com.insted.funcash.models.Crianca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriancaRequestDTO {
    private String nome;
    private String email;
    private int idade;
    private String senha;

    public CriancaRequestDTO(Crianca crianca) {
        this.email = crianca.getEmail();
        this.nome = crianca.getNome();
        this.idade = crianca.getIdade();
        this.senha = crianca.getSenha();

    }

}
