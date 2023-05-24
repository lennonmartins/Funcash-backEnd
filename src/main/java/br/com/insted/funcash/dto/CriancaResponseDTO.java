package br.com.insted.funcash.dto;

import java.time.LocalDate;

import br.com.insted.funcash.models.Crianca;
import br.com.insted.funcash.models.Genero;
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
    
    private LocalDate dataDeNascimento;
    
    private String email;
    
    private String senha;

    private double saldo;
    
    private String nome;

    private String apelido;

    private Genero genero;

    public CriancaResponseDTO(Crianca crianca) {
        this.id = crianca.getId();
        this.dataDeNascimento = crianca.getDataDeNascimento();
        this.email = crianca.getEmail();
        this.senha = crianca.getSenha();
        this.saldo = crianca.getSaldo();
        this.nome = crianca.getNome();
        this.apelido = crianca.getApelido();
        this.genero = crianca.getGenero();
    }   
}
