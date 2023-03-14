package br.com.insted.funcash.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
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
