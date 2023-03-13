package br.com.insted.funcash.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double hora_limite;
    private String data_limite;
    private double moeda;
    private String nome;
    public Tarefa(double hora_limite,String data_limite, double moeda, String nome) {
        this.hora_limite = hora_limite;
        this.data_limite = data_limite;
        this.moeda = moeda;
        this.nome = nome;
}
}
