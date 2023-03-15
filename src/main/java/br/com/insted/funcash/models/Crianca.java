package br.com.insted.funcash.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Crianca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 15)
    private String email;
    @Column(nullable = false, length = 15)
    private String senha;
    @Column(nullable = false, length = 15)
    private String nome;
    @Column(nullable = false, length = 15)
    private int idade;

    public Crianca(String email, String senha, String nome, int idade) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.idade = idade;
    }

    
    
}
