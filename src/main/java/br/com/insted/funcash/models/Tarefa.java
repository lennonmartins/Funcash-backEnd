package br.com.insted.funcash.models;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private double hora_limite;

    @Column(nullable = false)
    private Date data_limite;

    @Column(nullable = false)
    private Date data_de_criacao;

    @Column(nullable = false)
    private double moeda;

    @Column(nullable = false, length = 50)
    private String nome;

    public Tarefa(double hora_limite,Date data_limite, Date data_de_criacao, double moeda, String nome) {
        this.hora_limite = hora_limite;
        this.data_limite = data_limite;
        this.data_de_criacao = data_de_criacao;
        this.moeda = moeda;
        this.nome = nome;
}
}
