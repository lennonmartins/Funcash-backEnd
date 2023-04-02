package br.com.insted.funcash.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dataDeNascimento;

    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 16)
    private String senha;

    @Column(nullable = false, length = 15)
    private double saldo;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = true, length = 50)
    private String apelido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Genero genero;




    public Crianca(Date dataDeNascimento, @Email String email, String senha, double saldo, String nome,
            String apelido, Genero genero) {
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
        this.nome = nome;
        this.apelido = apelido;
        this.genero = genero;
    }

    

}
