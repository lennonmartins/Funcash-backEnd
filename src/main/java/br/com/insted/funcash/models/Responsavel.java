package br.com.insted.funcash.models;

import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDateTime dataDeNascimentoResponsavel;

    @Column(nullable = false)
    private String genero;
    
    @Column(nullable = false)
    @Lob
    private byte[] foto;
    
    @Column(nullable = false)
    private int senha;
    

    public Responsavel(String nome, String email, String cpf, LocalDateTime dataDeNascimentoResponsavel, String genero, byte[] foto, int senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataDeNascimentoResponsavel = dataDeNascimentoResponsavel;
        this.genero = genero;
        this.foto = foto;
        this.senha = senha;
    }

    
}
