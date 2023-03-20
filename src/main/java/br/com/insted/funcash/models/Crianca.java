package br.com.insted.funcash.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 15)
    private Date dataDeNascimento;

    @Email
    @Column(nullable = false, length = 15)
    private String email;

    @Column(nullable = false, length = 15)
    private String senha;

    @Column(nullable = false, length = 15)
    private double saldo;

    @Column(nullable = false, length = 15)
    private String nome;

    @Column(nullable = true, length = 15)
    private String apelido;


    public Crianca(Date dataDeNascimento, @Email String email, String senha, double saldo, String nome,
            String apelido) {
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
        this.nome = nome;
        this.apelido = apelido;
    }

}
