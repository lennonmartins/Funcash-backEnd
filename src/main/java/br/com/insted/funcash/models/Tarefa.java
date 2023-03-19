package br.com.insted.funcash.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, length = 15)
    private double hora_limite;

    @Column(nullable = false, length = 15)
    private String data_limite;

    @Column(nullable = false, length = 15)
    private double moeda;

    @Column(nullable = false, length = 15)
    private String nome;

    public Tarefa(double hora_limite,String data_limite, double moeda, String nome) {
        this.hora_limite = hora_limite;
        this.data_limite = data_limite;
        this.moeda = moeda;
        this.nome = nome;
}
}
