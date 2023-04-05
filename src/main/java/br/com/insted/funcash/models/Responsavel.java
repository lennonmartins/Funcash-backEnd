package br.com.insted.funcash.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    private LocalDate dataDeNascimentoResponsavel;

    @Column(nullable = false)
    private Genero genero;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "responsavel")
    private List<Crianca> criancas;

    public Responsavel(String nome, String email, String cpf, LocalDate dataDeNascimentoResponsavel, Genero genero,
            String senha) throws Exception {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataDeNascimentoResponsavel = dataDeNascimentoResponsavel;
        this.genero = genero;
        this.senha = senha;
    }

}
