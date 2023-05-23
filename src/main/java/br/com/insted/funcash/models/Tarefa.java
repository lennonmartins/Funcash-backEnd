package br.com.insted.funcash.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime horaLimite;

    @Column(nullable = false)
    private LocalDate dataLimite;

    @Column(nullable = false)
    private LocalDateTime dataDeCriacao;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false, length = 50)
    private String nome;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="crianca_id", nullable = true)
    private Crianca crianca;

    public Tarefa(LocalDateTime horaLimite,LocalDate dataLimite, double valor, String nome
    , Crianca crianca) {
        this.horaLimite = horaLimite;
        this.dataLimite = dataLimite;
        this.valor = valor;
        this.nome = nome;
        this.dataDeCriacao = LocalDateTime.now();
        this.crianca = crianca;
}

}
