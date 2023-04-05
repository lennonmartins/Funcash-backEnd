package br.com.insted.funcash.models;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String horaLimite;

    @Column(nullable = false)
    private LocalDate dataLimite;

    @Column(nullable = false)
    private LocalDateTime dataDeCriacao;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false, length = 50)
    private String nome;

    public Tarefa(String horaLimite,LocalDate dataLimite, double valor, String nome) {
        this.horaLimite = horaLimite;
        this.dataLimite = dataLimite;
        this.valor = valor;
        this.nome = nome;
        this.dataDeCriacao = LocalDateTime.now();
}
}
