package br.com.insted.funcash.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Crianca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String senha;
    private String nome;
    private int idade;

    public Crianca(String email, String senha, String nome, int idade) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.idade = idade;
    }
}
