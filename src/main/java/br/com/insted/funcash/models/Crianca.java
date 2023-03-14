package br.com.insted.funcash.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
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
