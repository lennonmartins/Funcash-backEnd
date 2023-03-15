package br.com.insted.funcash.dto;

import br.com.insted.funcash.models.Crianca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriancaResponseDTO {
    private Long id;

    private String email;

    private String senha;

    private String nome;

    private int idade;
    public CriancaResponseDTO(Crianca crianca) {
        this.id = crianca.getId();
        this.email = crianca.getEmail();
        this.nome = crianca.getNome();
        this.idade = crianca.getIdade();
        this.senha = crianca.getSenha();

    }
}
